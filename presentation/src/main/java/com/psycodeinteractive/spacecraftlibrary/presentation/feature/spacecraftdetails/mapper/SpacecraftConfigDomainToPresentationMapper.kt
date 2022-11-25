package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftConfigDomainModel
import com.psycodeinteractive.spacecraftlibrary.presentation.contract.mapper.DomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.SpacecraftConfigPresentationModel

class SpacecraftConfigDomainToPresentationMapper(
    private val typeDomainToPresentationMapper: TypeDomainToPresentationMapper,
    private val agencyDomainToPresentationMapper: AgencyDomainToPresentationMapper,
) : DomainToPresentationMapper<SpacecraftConfigDomainModel, SpacecraftConfigPresentationModel>() {
    override fun map(input: SpacecraftConfigDomainModel) = SpacecraftConfigPresentationModel(
        id = input.id,
        url = input.url,
        name = input.name,
        type = typeDomainToPresentationMapper.toPresentation(input.type),
        agency = agencyDomainToPresentationMapper.toPresentation(input.agency),
        isInUse = input.isInUse,
        imageUrl = input.imageUrl
    )
}
