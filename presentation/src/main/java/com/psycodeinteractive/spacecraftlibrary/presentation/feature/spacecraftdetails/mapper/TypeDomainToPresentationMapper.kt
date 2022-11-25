package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper

import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.TypeDomainModel
import com.psycodeinteractive.spacecraftlibrary.presentation.contract.mapper.DomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.TypePresentationModel

class TypeDomainToPresentationMapper : DomainToPresentationMapper<TypeDomainModel, TypePresentationModel>() {
    override fun map(input: TypeDomainModel) = TypePresentationModel(
        id = input.id,
        name = input.name
    )
}
