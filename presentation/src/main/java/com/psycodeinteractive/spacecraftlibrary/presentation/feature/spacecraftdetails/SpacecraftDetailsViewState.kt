package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails

import com.psycodeinteractive.spacecraftlibrary.presentation.ViewState
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.SpacecraftPresentationModel

data class SpacecraftDetailsViewState(
    var spacecraft: SpacecraftPresentationModel? = null
) : ViewState()
