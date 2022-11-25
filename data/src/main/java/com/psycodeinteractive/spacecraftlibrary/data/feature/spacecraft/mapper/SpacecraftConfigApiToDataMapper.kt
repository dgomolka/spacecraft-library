package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.ApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.AgencyDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftConfigDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote.SpacecraftConfigApiModel

class SpacecraftConfigApiToDataMapper(
    private val typeApiToDataMapper: TypeApiToDataMapper,
    private val agencyApiToDataMapper: AgencyApiToDataMapper
) : ApiToDataMapper<SpacecraftConfigApiModel, SpacecraftConfigDataModel>() {
    override fun map(input: SpacecraftConfigApiModel) = SpacecraftConfigDataModel(
        id = input.id,
        url = input.url,
        name = input.name,
        type = typeApiToDataMapper.toData(input.type),
        agency = input.agency?.let(agencyApiToDataMapper::toData) ?: AgencyDataModel.Unspecified,
        isInUse = input.isInUse,
        imageUrl = input.imageUrl.orEmpty()
    )
}
