@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)

package com.psycodeinteractive.spacecraftlibrary.ui.feature.launch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.launch.LaunchViewModel
import com.psycodeinteractive.spacecraftlibrary.ui.SpacecraftLibraryTheme
import com.psycodeinteractive.spacecraftlibrary.ui.feature.NavGraphs
import com.psycodeinteractive.spacecraftlibrary.ui.feature.destinations.SpacecraftDetailsScreenDestination
import com.psycodeinteractive.spacecraftlibrary.ui.feature.destinations.SpacecraftListScreenDestination
import com.psycodeinteractive.spacecraftlibrary.ui.feature.destinations.SplashScreenDestination
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.SpacecraftDetailsScreen
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper.SpacecraftPresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftlist.SpacecraftListScreen
import com.psycodeinteractive.spacecraftlibrary.ui.feature.splash.SplashScreen
import com.psycodeinteractive.spacecraftlibrary.ui.mapper.exception.DefaultPresentationToUiExceptionMapper
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations.Companion.ACCOMPANIST_FADING
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LaunchActivity : ComponentActivity() {

    @Inject
    protected lateinit var spacecraftPresentationToUiMapper: SpacecraftPresentationToUiMapper

    @Inject
    protected lateinit var defaultPresentationToUiExceptionMapper: DefaultPresentationToUiExceptionMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpacecraftLibraryTheme {
                AppEntryPoint(
                    spacecraftPresentationToUiMapper,
                    defaultPresentationToUiExceptionMapper
                )
            }
        }
    }
}

@Composable
fun AppEntryPoint(
    spacecraftPresentationToUiMapper: SpacecraftPresentationToUiMapper,
    defaultPresentationToUiExceptionMapper: DefaultPresentationToUiExceptionMapper
) {
    val viewModel: LaunchViewModel = hiltViewModel()
    LocalLifecycleOwner.current.run {
        DisposableEffect(this) {
            val observer = viewModel.lifecycleEventObserver
            with(lifecycle) {
                addObserver(observer)
                onDispose {
                    removeObserver(observer)
                }
            }
        }
    }

    val navHostEngine = rememberAnimatedNavHostEngine(
        navHostContentAlignment = TopCenter,
        rootDefaultAnimations = ACCOMPANIST_FADING
    )

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine
    ) {
        composable(SplashScreenDestination) {
            SplashScreen(
                presentationToUiExceptionMapper = defaultPresentationToUiExceptionMapper
            ) {
                destinationsNavigator.popBackStack()
                destinationsNavigator.navigate(SpacecraftListScreenDestination) {
                    launchSingleTop = true
                }
            }
        }
        composable(SpacecraftListScreenDestination) {
            SpacecraftListScreen(
                spacecraftPresentationToUiMapper = spacecraftPresentationToUiMapper,
                presentationToUiExceptionMapper = defaultPresentationToUiExceptionMapper
            ) { spacecraftId ->
                destinationsNavigator.navigate(SpacecraftDetailsScreenDestination(spacecraftId = spacecraftId))
            }
        }
        composable(SpacecraftDetailsScreenDestination) {
            SpacecraftDetailsScreen(
                presentationToUiExceptionMapper = defaultPresentationToUiExceptionMapper,
                spacecraftPresentationToUiMapper = spacecraftPresentationToUiMapper,
                spacecraftId = navArgs.spacecraftId
            ) {
                destinationsNavigator.popBackStack()
            }
        }
    }
}

private val LaunchViewModel.lifecycleEventObserver
    get() = LifecycleEventObserver { _, event ->
        if (event == ON_START) {
            onStart()
        } else if (event == ON_STOP) {
            onStop()
        }
    }
