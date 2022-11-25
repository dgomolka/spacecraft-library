package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import com.psycodeinteractive.spacecraftlibrary.presentation.contract.mapper.DomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.SpacecraftPresentationModel

class SpacecraftDomainToPresentationMapper(
    private val statusDomainToPresentationMapper: StatusDomainToPresentationMapper,
    private val spacecraftConfigDomainToPresentationMapper: SpacecraftConfigDomainToPresentationMapper
) : DomainToPresentationMapper<SpacecraftDomainModel, SpacecraftPresentationModel>() {
    override fun map(input: SpacecraftDomainModel) = SpacecraftPresentationModel(
        id = input.id,
        url = input.url,
        name = input.name,
        serialNumber = input.serialNumber,
        status = statusDomainToPresentationMapper.toPresentation(input.status),
        description = input.description,
        spacecraftConfig = spacecraftConfigDomainToPresentationMapper.toPresentation(input.spacecraftConfig)
    )
}
