package com.psycodeinteractive.spacecraftlibrary.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.psycodeinteractive.spacecraftlibrary.presentation.BaseViewModel
import com.psycodeinteractive.spacecraftlibrary.presentation.Event
import com.psycodeinteractive.spacecraftlibrary.presentation.ViewState
import com.psycodeinteractive.spacecraftlibrary.presentation.model.exception.PresentationException
import com.psycodeinteractive.spacecraftlibrary.ui.contract.mapper.PresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.model.exception.UiException
import com.psycodeinteractive.spacecraftlibrary.ui.widget.ErrorDialog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
inline fun <reified VM : BaseViewModel<VS, E>, reified VS : ViewState, reified E : Event> Screen(
    viewModel: VM = hiltViewModel(),
    presentationToUiExceptionMapper: PresentationToUiMapper<PresentationException, UiException>,
    crossinline children: @Composable (viewModel: VM, viewState: VS, lifecycleScope: LifecycleCoroutineScope) -> Unit
) {
    val viewState by viewModel.collectViewState()
    OnLifecycle(minActiveState = CREATED) {
        viewModel.onViewCreated()
    }

    HandleErrors(viewModel, presentationToUiExceptionMapper)

    children(viewModel, viewState, LocalLifecycleOwner.current.lifecycleScope)
}

@Composable
inline fun <reified VM : BaseViewModel<VS, E>, reified VS : ViewState, reified E : Event> HandleErrors(
    viewModel: VM,
    presentationToUiExceptionMapper: PresentationToUiMapper<PresentationException, UiException>
) {
    val resources = LocalContext.current.resources
    var uiException by remember { mutableStateOf<UiException?>(null) }
    uiException?.run {
        ErrorDialog(
            title = getTitleText(resources),
            message = getMessageText(resources)
        ) { showDialog ->
            if (!showDialog) {
                uiException = null
            }
        }
    }
    viewModel.exceptionFlow.observeWithLifecycle { presentationException ->
        uiException = presentationToUiExceptionMapper.toUi(presentationException)
    }
}

@Composable
inline fun <VM : BaseViewModel<VS, out Event>, reified VS : ViewState>
VM.collectViewState(): MutableState<VS> {
    val composeState = viewState.collectAsState(initialViewState.wrap())
    val newState = remember { mutableStateOf(initialViewState) }
    newState.value = composeState.value.state
    return newState
}

@Composable
inline fun <reified Model> Flow<Model>.observeWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = STARTED,
    noinline action: suspend (Model) -> Unit
) {
    OnLifecycle(lifecycleOwner, minActiveState) {
        flowWithLifecycle(
            lifecycleOwner.lifecycle,
            minActiveState
        ).collect { value -> action(value) }
    }
}

@Composable
fun OnLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = STARTED,
    action: suspend () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        with(lifecycleOwner) {
            lifecycleScope.launch {
                repeatOnLifecycle(minActiveState) {
                    action()
                }
            }
        }
    }
}
