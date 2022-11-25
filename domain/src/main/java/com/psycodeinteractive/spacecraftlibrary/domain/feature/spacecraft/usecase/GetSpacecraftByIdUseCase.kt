package com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase

import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.CoroutineContextProvider
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BackgroundExecutionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BaseUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.repository.SpacecraftRepository
import kotlinx.coroutines.CoroutineScope

interface GetSpacecraftByIdUseCase : BaseUseCase<Int, SpacecraftDomainModel>

class GetSpacecraftByIdUseCaseImpl(
    private val spacecraftRepository: SpacecraftRepository,
    coroutineContextProvider: CoroutineContextProvider
) : GetSpacecraftByIdUseCase, BackgroundExecutionUseCase<Int, SpacecraftDomainModel>(coroutineContextProvider) {
    override suspend fun executeInBackground(request: Int, coroutineScope: CoroutineScope) =
        spacecraftRepository.getSpacecraft(request)
}
