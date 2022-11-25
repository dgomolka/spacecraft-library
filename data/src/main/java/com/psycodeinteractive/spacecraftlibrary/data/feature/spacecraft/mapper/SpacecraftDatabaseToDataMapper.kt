package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftDatabaseModel

class SpacecraftDatabaseToDataMapper(
    private val statusDatabaseToDataMapper: StatusDatabaseToDataMapper,
    private val spacecraftConfigDatabaseToDataMapper: SpacecraftConfigDatabaseToDataMapper
) : DatabaseToDataMapper<SpacecraftDatabaseModel, SpacecraftDataModel>() {
    override fun map(input: SpacecraftDatabaseModel) = SpacecraftDataModel(
        id = input.id,
        url = input.url,
        name = input.name,
        serialNumber = input.serialNumber,
        status = statusDatabaseToDataMapper.toData(input.status),
        description = input.description,
        spacecraftConfig = spacecraftConfigDatabaseToDataMapper.toData(input.spacecraftConfig)
    )
}
