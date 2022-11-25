package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftConfigDataModel
import com.psycodeinteractive.spacecraftlibrary.domain.contract.mapper.DataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftConfigDomainModel

class SpacecraftConfigDataToDomainMapper(
    private val typeDataToDomainMapper: TypeDataToDomainMapper,
    private val agencyDataToDomainMapper: AgencyDataToDomainMapper
) : DataToDomainMapper<SpacecraftConfigDataModel, SpacecraftConfigDomainModel>() {
    override fun map(input: SpacecraftConfigDataModel) = SpacecraftConfigDomainModel(
        id = input.id,
        url = input.url,
        name = input.name,
        type = typeDataToDomainMapper.toDomain(input.type),
        agency = agencyDataToDomainMapper.toDomain(input.agency),
        isInUse = input.isInUse,
        imageUrl = input.imageUrl
    )
}
