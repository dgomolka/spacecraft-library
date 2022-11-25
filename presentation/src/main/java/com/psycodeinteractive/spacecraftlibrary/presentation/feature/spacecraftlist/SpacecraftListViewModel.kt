package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftlist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase.GetSpacecraftListUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.presentation.BaseViewModel
import com.psycodeinteractive.spacecraftlibrary.presentation.execution.UseCaseExecutorProvider
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.SpacecraftDetailsPresentationDestination
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper.SpacecraftDomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.mapper.DefaultDomainToPresentationExceptionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SpacecraftListViewModel @Inject constructor(
    private val spacecraftDomainToPresentationMapper: SpacecraftDomainToPresentationMapper,
    private val getSpacecraftListUseCase: GetSpacecraftListUseCase,
    override val domainToPresentationExceptionMapper: DefaultDomainToPresentationExceptionMapper,
    override val logger: Logger,
    override val useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<SpacecraftListViewState, SpacecraftListEvent>() {

    override val initialViewState = SpacecraftListViewState()

    private var isSpacecraftListFetched = false

    override fun onViewCreated() {
        fetchSpacecraftList()
    }

    private fun fetchSpacecraftList() {
        if (!isSpacecraftListFetched) {
            getSpacecraftListUseCase.execute(
                callback = ::updateSpacecraftListState,
                onError = logger::e
            )
            isSpacecraftListFetched = true
        }
    }

    private fun updateSpacecraftListState(spacecraftPagingData: Flow<PagingData<SpacecraftDomainModel>>) {
        updateViewState {
            spacecraftList = spacecraftPagingData.map { pagingData ->
                pagingData.map(spacecraftDomainToPresentationMapper::toPresentation)
            }.cachedIn(viewModelScope)
        }
    }

    fun onSpacecraftClick(spacecraftId: Int) {
        SpacecraftDetailsPresentationDestination(spacecraftId).navigate()
    }
}
