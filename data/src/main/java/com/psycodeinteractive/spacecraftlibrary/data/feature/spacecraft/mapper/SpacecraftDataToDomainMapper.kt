package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftDataModel
import com.psycodeinteractive.spacecraftlibrary.domain.contract.mapper.DataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel

class SpacecraftDataToDomainMapper(
    private val statusDataToDomainMapper: StatusDataToDomainMapper,
    private val spacecraftConfigDataToDomainMapper: SpacecraftConfigDataToDomainMapper
) : DataToDomainMapper<SpacecraftDataModel, SpacecraftDomainModel>() {
    override fun map(input: SpacecraftDataModel) = SpacecraftDomainModel(
        id = input.id,
        url = input.url,
        name = input.name,
        serialNumber = input.serialNumber,
        status = statusDataToDomainMapper.toDomain(input.status),
        description = input.description,
        spacecraftConfig = spacecraftConfigDataToDomainMapper.toDomain(input.spacecraftConfig)
    )
}
