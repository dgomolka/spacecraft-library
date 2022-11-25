package com.psycodeinteractive.spacecraftlibrary.presentation.feature.splash

import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.presentation.BaseViewModel
import com.psycodeinteractive.spacecraftlibrary.presentation.execution.UseCaseExecutorProvider
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftlist.SpacecraftListPresentationDestination
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.splash.SplashEvent.StartSplash
import com.psycodeinteractive.spacecraftlibrary.presentation.mapper.DefaultDomainToPresentationExceptionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    override val domainToPresentationExceptionMapper: DefaultDomainToPresentationExceptionMapper,
    override val logger: Logger,
    override val useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<SplashViewState, SplashEvent>() {

    override val initialViewState = SplashViewState

    override fun onViewCreated() {
        StartSplash.dispatchEvent()
    }

    fun onSplashFinished() {
        SpacecraftListPresentationDestination.navigate()
    }
}
