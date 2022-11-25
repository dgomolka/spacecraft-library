package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftConfigDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftConfigDatabaseModel

class SpacecraftConfigDatabaseToDataMapper(
    private val typeDatabaseToDataMapper: TypeDatabaseToDataMapper,
    private val agencyDatabaseToDataMapper: AgencyDatabaseToDataMapper
) : DatabaseToDataMapper<SpacecraftConfigDatabaseModel, SpacecraftConfigDataModel>() {
    override fun map(input: SpacecraftConfigDatabaseModel) = SpacecraftConfigDataModel(
        id = input.id,
        url = input.url,
        name = input.name,
        type = typeDatabaseToDataMapper.toData(input.type),
        agency = agencyDatabaseToDataMapper.toData(input.agency),
        isInUse = input.isInUse,
        imageUrl = input.imageUrl
    )
}
