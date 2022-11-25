package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.AgencyPresentationModel
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.AgencyPresentationModel.AgencyInfoPresentationModel
import com.psycodeinteractive.spacecraftlibrary.ui.contract.mapper.PresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.AgencyUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.AgencyUiModel.AgencyInfoUiModel

class AgencyPresentationToUiMapper : PresentationToUiMapper<AgencyPresentationModel, AgencyUiModel>() {
    override fun map(input: AgencyPresentationModel) = when (input) {
        is AgencyInfoPresentationModel -> AgencyInfoUiModel(
            id = input.id,
            url = input.url,
            name = input.name,
            type = input.type
        )
        AgencyPresentationModel.Unspecified -> AgencyUiModel.Unspecified
    }
}
