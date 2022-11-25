package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.StatusDomainModel
import com.psycodeinteractive.spacecraftlibrary.presentation.contract.mapper.DomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.StatusPresentationModel

class StatusDomainToPresentationMapper : DomainToPresentationMapper<StatusDomainModel, StatusPresentationModel>() {
    override fun map(input: StatusDomainModel) = StatusPresentationModel(
        id = input.id,
        name = input.name
    )
}
