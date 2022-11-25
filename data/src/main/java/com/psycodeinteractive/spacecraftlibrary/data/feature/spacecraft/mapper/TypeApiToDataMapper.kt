package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.ApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.TypeDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote.TypeApiModel

class TypeApiToDataMapper : ApiToDataMapper<TypeApiModel, TypeDataModel>() {
    override fun map(input: TypeApiModel) = TypeDataModel(
        id = input.id,
        name = input.name
    )
}
