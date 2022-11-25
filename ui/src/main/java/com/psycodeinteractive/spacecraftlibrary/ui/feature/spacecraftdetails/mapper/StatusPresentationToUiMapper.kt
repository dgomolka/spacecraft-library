package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.StatusPresentationModel
import com.psycodeinteractive.spacecraftlibrary.ui.contract.mapper.PresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.StatusUiModel

class StatusPresentationToUiMapper : PresentationToUiMapper<StatusPresentationModel, StatusUiModel>() {
    override fun map(input: StatusPresentationModel) = StatusUiModel(
        id = input.id,
        name = input.name
    )
}
