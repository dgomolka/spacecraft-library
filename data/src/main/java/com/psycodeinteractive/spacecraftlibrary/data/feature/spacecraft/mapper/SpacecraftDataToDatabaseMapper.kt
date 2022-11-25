package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftDatabaseModel

class SpacecraftDataToDatabaseMapper(
    private val statusDataToDatabaseMapper: StatusDataToDatabaseMapper,
    private val spacecraftConfigDataToDatabaseMapper: SpacecraftConfigDataToDatabaseMapper
) : DataToDatabaseMapper<SpacecraftDataModel, SpacecraftDatabaseModel>() {
    override fun map(input: SpacecraftDataModel) = SpacecraftDatabaseModel(
        id = input.id,
        url = input.url,
        name = input.name,
        serialNumber = input.serialNumber,
        status = statusDataToDatabaseMapper.toDatabase(input.status),
        description = input.description,
        spacecraftConfig = spacecraftConfigDataToDatabaseMapper.toDatabase(input.spacecraftConfig)
    )
}
