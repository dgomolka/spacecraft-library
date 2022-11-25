package com.psycodeinteractive.spacecraftlibrary.presentation.feature.launch

import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StartSessionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StopSessionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.presentation.BaseViewModel
import com.psycodeinteractive.spacecraftlibrary.presentation.execution.UseCaseExecutorProvider
import com.psycodeinteractive.spacecraftlibrary.presentation.mapper.DefaultDomainToPresentationExceptionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val startSessionUseCase: StartSessionUseCase,
    private val stopSessionUseCase: StopSessionUseCase,
    override val domainToPresentationExceptionMapper: DefaultDomainToPresentationExceptionMapper,
    override val logger: Logger,
    override val useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<LaunchViewState, LaunchEvent>() {

    override val initialViewState = LaunchViewState

    fun onStart() {
        startSessionUseCase.execute()
    }

    fun onStop() {
        stopSessionUseCase.execute()
    }
}
