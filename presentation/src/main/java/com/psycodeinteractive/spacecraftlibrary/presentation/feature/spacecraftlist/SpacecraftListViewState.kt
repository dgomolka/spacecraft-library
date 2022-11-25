package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftlist

import androidx.paging.PagingData
import com.psycodeinteractive.spacecraftlibrary.presentation.ViewState
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.SpacecraftPresentationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SpacecraftListViewState(
    var spacecraftList: Flow<PagingData<SpacecraftPresentationModel>> = emptyFlow()
) : ViewState()
