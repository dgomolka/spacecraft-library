package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.SpacecraftPresentationModel
import com.psycodeinteractive.spacecraftlibrary.ui.contract.mapper.PresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.SpacecraftUiModel

class SpacecraftPresentationToUiMapper(
    private val statusPresentationToUiMapper: StatusPresentationToUiMapper,
    private val spacecraftConfigPresentationToUiMapper: SpacecraftConfigPresentationToUiMapper
) : PresentationToUiMapper<SpacecraftPresentationModel, SpacecraftUiModel>() {
    override fun map(input: SpacecraftPresentationModel) = SpacecraftUiModel(
        id = input.id,
        url = input.url,
        name = input.name,
        serialNumber = input.serialNumber,
        status = statusPresentationToUiMapper.toUi(input.status),
        description = input.description,
        spacecraftConfig = spacecraftConfigPresentationToUiMapper.toUi(input.spacecraftConfig)
    )
}
