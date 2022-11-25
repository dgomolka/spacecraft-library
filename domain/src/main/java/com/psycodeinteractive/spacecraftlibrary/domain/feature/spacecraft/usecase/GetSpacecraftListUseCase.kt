package com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase

import androidx.paging.PagingData
import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.CoroutineContextProvider
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BackgroundExecutionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BaseUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.repository.SpacecraftRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GetSpacecraftListUseCase : BaseUseCase<Unit, Flow<PagingData<SpacecraftDomainModel>>>

class GetSpacecraftListUseCaseImpl(
    private val spacecraftRepository: SpacecraftRepository,
    coroutineContextProvider: CoroutineContextProvider
) : GetSpacecraftListUseCase, BackgroundExecutionUseCase<Unit, Flow<PagingData<SpacecraftDomainModel>>>(coroutineContextProvider) {
    override suspend fun executeInBackground(request: Unit, coroutineScope: CoroutineScope) =
        spacecraftRepository.getSpacecraftList()
}
