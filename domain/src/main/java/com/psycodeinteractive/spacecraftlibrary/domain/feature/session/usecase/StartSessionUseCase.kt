package com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase

import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.CoroutineContextProvider
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BackgroundExecutionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BaseUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.repository.AppSessionRepository
import kotlinx.coroutines.CoroutineScope

interface StartSessionUseCase : BaseUseCase<Unit, Unit>

class StartSessionUseCaseImpl(
    private val appSessionRepository: AppSessionRepository,
    coroutineContextProvider: CoroutineContextProvider
) : StartSessionUseCase, BackgroundExecutionUseCase<Unit, Unit>(coroutineContextProvider) {
    override suspend fun executeInBackground(request: Unit, coroutineScope: CoroutineScope) =
        appSessionRepository.startSession()
}
