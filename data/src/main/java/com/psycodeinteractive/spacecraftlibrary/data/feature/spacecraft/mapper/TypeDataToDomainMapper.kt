package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.TypeDataModel
import com.psycodeinteractive.spacecraftlibrary.domain.contract.mapper.DataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.TypeDomainModel

class TypeDataToDomainMapper : DataToDomainMapper<TypeDataModel, TypeDomainModel>() {
    override fun map(input: TypeDataModel) = TypeDomainModel(
        id = input.id,
        name = input.name
    )
}
