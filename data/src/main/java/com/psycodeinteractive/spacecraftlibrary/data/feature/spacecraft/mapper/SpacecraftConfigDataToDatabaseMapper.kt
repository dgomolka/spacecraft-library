package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftConfigDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftConfigDatabaseModel

class SpacecraftConfigDataToDatabaseMapper(
    private val typeDataToDatabaseMapper: TypeDataToDatabaseMapper,
    private val agencyDataToDatabaseMapper: AgencyDataToDatabaseMapper,
) : DataToDatabaseMapper<SpacecraftConfigDataModel, SpacecraftConfigDatabaseModel>() {
    override fun map(input: SpacecraftConfigDataModel) = SpacecraftConfigDatabaseModel(
        id = input.id,
        url = input.url,
        name = input.name,
        type = typeDataToDatabaseMapper.toDatabase(input.type),
        agency = agencyDataToDatabaseMapper.toDatabase(input.agency),
        isInUse = input.isInUse,
        imageUrl = input.imageUrl
    )
}
