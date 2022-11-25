package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails

import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase.GetSpacecraftByIdUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.presentation.BaseViewModel
import com.psycodeinteractive.spacecraftlibrary.presentation.execution.UseCaseExecutorProvider
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper.SpacecraftDomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.mapper.DefaultDomainToPresentationExceptionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpacecraftDetailsViewModel @Inject constructor(
    private val spacecraftDomainToPresentationMapper: SpacecraftDomainToPresentationMapper,
    private val getSpacecraftByIdUseCase: GetSpacecraftByIdUseCase,
    override val domainToPresentationExceptionMapper: DefaultDomainToPresentationExceptionMapper,
    override val logger: Logger,
    override val useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<SpacecraftDetailsViewState, SpacecraftDetailsEvent>() {

    override val initialViewState = SpacecraftDetailsViewState()

    fun fetchSpacecraftDetails(spacecraftId: Int) {
        getSpacecraftByIdUseCase.execute(
            callback = ::updateSpacecraftState,
            value = spacecraftId,
            onError = logger::e
        )
    }

    fun onViewCreated(spacecraftId: Int) {
        fetchSpacecraftDetails(spacecraftId)
    }

    private fun updateSpacecraftState(spacecraftDomainModel: SpacecraftDomainModel) {
        updateViewState {
            spacecraft = spacecraftDomainToPresentationMapper.toPresentation(
                spacecraftDomainModel
            )
        }
    }
}
