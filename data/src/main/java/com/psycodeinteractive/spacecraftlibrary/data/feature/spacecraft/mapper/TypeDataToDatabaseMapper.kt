package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.TypeDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.TypeDatabaseModel

class TypeDataToDatabaseMapper : DataToDatabaseMapper<TypeDataModel, TypeDatabaseModel>() {
    override fun map(input: TypeDataModel) = TypeDatabaseModel(
        id = input.id,
        name = input.name
    )
}
