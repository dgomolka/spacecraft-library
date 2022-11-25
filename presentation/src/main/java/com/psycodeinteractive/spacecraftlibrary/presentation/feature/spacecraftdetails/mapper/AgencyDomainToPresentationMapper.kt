package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.AgencyDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.AgencyDomainModel.AgencyInfoDomainModel
import com.psycodeinteractive.spacecraftlibrary.presentation.contract.mapper.DomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.AgencyPresentationModel

class AgencyDomainToPresentationMapper : DomainToPresentationMapper<AgencyDomainModel, AgencyPresentationModel>() {
    override fun map(input: AgencyDomainModel) = when (input) {
        is AgencyInfoDomainModel -> AgencyPresentationModel.AgencyInfoPresentationModel(
            id = input.id,
            url = input.url,
            name = input.name,
            type = input.type
        )
        AgencyDomainModel.Unspecified -> AgencyPresentationModel.Unspecified
    }
}
