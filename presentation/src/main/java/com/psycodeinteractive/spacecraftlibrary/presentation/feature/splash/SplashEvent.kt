package com.psycodeinteractive.spacecraftlibrary.presentation.feature.splash

import com.psycodeinteractive.spacecraftlibrary.presentation.Event

sealed class SplashEvent : Event {
    object StartSplash : SplashEvent()
}
