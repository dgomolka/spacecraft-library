package com.psycodeinteractive.spacecraftlibrary.ui.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle.State.CREATED
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftlist.SpacecraftListPresentationDestination
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.splash.SplashEvent.StartSplash
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.splash.SplashViewModel
import com.psycodeinteractive.spacecraftlibrary.ui.R
import com.psycodeinteractive.spacecraftlibrary.ui.extension.value
import com.psycodeinteractive.spacecraftlibrary.ui.mapper.exception.DefaultPresentationToUiExceptionMapper
import com.psycodeinteractive.spacecraftlibrary.ui.screen.OnLifecycle
import com.psycodeinteractive.spacecraftlibrary.ui.screen.Screen
import com.psycodeinteractive.spacecraftlibrary.ui.screen.observeWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.coroutines.delay

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    presentationToUiExceptionMapper: DefaultPresentationToUiExceptionMapper,
    goToSpacecraftList: () -> Unit
) {
    Screen<SplashViewModel, _, _>(
        presentationToUiExceptionMapper = presentationToUiExceptionMapper
    ) { viewModel, _, _ ->
        OnLifecycle(minActiveState = CREATED) {
            viewModel.onViewCreated()
        }
        Splash()
        HandleEvents(viewModel)
        HandleNavigation(viewModel, goToSpacecraftList)
    }
}

@Composable
private fun Splash() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Center)
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = R.string.app_name.value,
            fontSize = 32.sp,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun HandleEvents(
    viewModel: SplashViewModel
) {
    viewModel.eventFlow.observeWithLifecycle { event ->
        when (event) {
            StartSplash -> startSplash(viewModel)
        }
    }
}

@Composable
private fun HandleNavigation(
    viewModel: SplashViewModel,
    goToSpacecraftList: () -> Unit
) {
    viewModel.navigationFlow.observeWithLifecycle { destination ->
        when (destination) {
            SpacecraftListPresentationDestination -> goToSpacecraftList()
        }
    }
}

private suspend fun startSplash(viewModel: SplashViewModel) {
    delay(splashTimeMilliseconds)
    viewModel.onSplashFinished()
}

private const val splashTimeMilliseconds = 2000L
