package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.SpacecraftConfigPresentationModel
import com.psycodeinteractive.spacecraftlibrary.ui.contract.mapper.PresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.SpacecraftConfigUiModel

class SpacecraftConfigPresentationToUiMapper(
    private val typePresentationToUiMapper: TypePresentationToUiMapper,
    private val agencyPresentationToUiMapper: AgencyPresentationToUiMapper
) : PresentationToUiMapper<SpacecraftConfigPresentationModel, SpacecraftConfigUiModel>() {
    override fun map(input: SpacecraftConfigPresentationModel) = SpacecraftConfigUiModel(
        id = input.id,
        url = input.url,
        name = input.name,
        type = typePresentationToUiMapper.toUi(input.type),
        agency = agencyPresentationToUiMapper.toUi(input.agency),
        isInUse = input.isInUse,
        imageUrl = input.imageUrl
    )
}
