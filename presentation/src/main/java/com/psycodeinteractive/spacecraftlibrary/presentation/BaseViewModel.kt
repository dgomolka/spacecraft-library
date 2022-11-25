package com.psycodeinteractive.spacecraftlibrary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psycodeinteractive.spacecraftlibrary.domain.execution.UseCaseExecutor
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BaseUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.domain.model.exception.DomainException
import com.psycodeinteractive.spacecraftlibrary.presentation.contract.mapper.DomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.destination.BackDestination
import com.psycodeinteractive.spacecraftlibrary.presentation.execution.UseCaseExecutorProvider
import com.psycodeinteractive.spacecraftlibrary.presentation.model.exception.CustomPresentationException
import com.psycodeinteractive.spacecraftlibrary.presentation.model.exception.PresentationException
import com.psycodeinteractive.spacecraftlibrary.presentation.navigation.PresentationDestination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<BaseState : ViewState, BaseEvent : Event> : ViewModel() {

    abstract val domainToPresentationExceptionMapper: DomainToPresentationMapper<DomainException, PresentationException>

    abstract val logger: Logger

    abstract val useCaseExecutorProvider: UseCaseExecutorProvider

    private val useCaseExecutor: UseCaseExecutor by lazy { useCaseExecutorProvider(viewModelScope) }

    private val _viewState by lazy { MutableStateFlow(initialViewState.wrap()) }
    val viewState by lazy { _viewState.asStateFlow() }

    private val _eventChannel by lazy { Channel<BaseEvent>(BUFFERED) }
    val eventFlow by lazy { _eventChannel.receiveAsFlow() }

    private val _navigationChannel by lazy { Channel<PresentationDestination>(BUFFERED) }
    val navigationFlow by lazy { _navigationChannel.receiveAsFlow() }

    private val _exceptionChannel by lazy { Channel<PresentationException>(BUFFERED) }
    val exceptionFlow by lazy { _exceptionChannel.receiveAsFlow() }

    abstract val initialViewState: BaseState

    protected fun updateViewState(mutation: BaseState.() -> Unit) {
        val viewState = _viewState.value.state
        mutation(viewState)
        _viewState.value = viewState.wrap()
    }

    protected fun BaseEvent.dispatchEvent() {
        _eventChannel.trySend(this@dispatchEvent)
            .handleChannelException()
    }

    protected fun PresentationDestination.navigate() {
        _navigationChannel.trySend(this@navigate)
            .handleChannelException()
    }

    fun currentViewState() = viewState.value.state

    internal fun <Output> BaseUseCase<Unit, Output>.execute(
        callback: (Output) -> Unit = {},
        onError: (PresentationException) -> Unit = ::notifyError
    ) = useCaseExecutor.execute(this, callback) { exception ->
        onError(domainToPresentationExceptionMapper.toPresentation(exception))
    }

    internal fun <Input, Output> BaseUseCase<Input, Output>.execute(
        value: Input,
        callback: (Output) -> Unit = {},
        onError: (PresentationException) -> Unit = ::notifyError
    ) = useCaseExecutor.execute(this, value, callback) { exception ->
        onError(domainToPresentationExceptionMapper.toPresentation(exception))
    }

    protected fun notifyError(exception: PresentationException) {
        logger.e(exception)
        _exceptionChannel.trySend(exception)
            .handleChannelException()
    }

    private fun ChannelResult<Any>.handleChannelException() = onFailure { exceptionChannelException ->
        exceptionChannelException?.let(logger::e)
    }

    open fun onViewCreated() {}

    fun onCloseAction() {
        BackDestination.navigate()
    }

    fun onError(message: String) {
        notifyError(CustomPresentationException(message))
    }

    fun BaseState.wrap() = StateWrapper(this)

    class StateWrapper<BaseState : ViewState>(val state: BaseState)
}
