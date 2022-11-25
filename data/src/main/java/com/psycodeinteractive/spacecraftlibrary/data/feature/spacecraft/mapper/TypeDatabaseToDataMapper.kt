package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.TypeDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.TypeDatabaseModel

class TypeDatabaseToDataMapper : DatabaseToDataMapper<TypeDatabaseModel, TypeDataModel>() {
    override fun map(input: TypeDatabaseModel) = TypeDataModel(
        id = input.id,
        name = input.name
    )
}
