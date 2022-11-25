package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.ApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote.SpacecraftApiModel

class SpacecraftApiToDataMapper(
    private val statusApiToDataMapper: StatusApiToDataMapper,
    private val spacecraftConfigApiToDataMapper: SpacecraftConfigApiToDataMapper
) : ApiToDataMapper<SpacecraftApiModel, SpacecraftDataModel>() {
    override fun map(input: SpacecraftApiModel) = SpacecraftDataModel(
        id = input.id,
        url = input.url,
        name = input.name,
        serialNumber = input.serialNumber.orEmpty(),
        status = statusApiToDataMapper.toData(input.status),
        description = input.description,
        spacecraftConfig = spacecraftConfigApiToDataMapper.toData(input.spacecraftConfig)
    )
}
