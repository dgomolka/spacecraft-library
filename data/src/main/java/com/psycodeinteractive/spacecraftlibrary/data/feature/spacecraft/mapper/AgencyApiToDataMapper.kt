package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.ApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.AgencyDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote.AgencyApiModel

class AgencyApiToDataMapper : ApiToDataMapper<AgencyApiModel, AgencyDataModel>() {
    override fun map(input: AgencyApiModel) = AgencyDataModel.AgencyInfoDataModel(
        id = input.id,
        url = input.url,
        name = input.name,
        type = input.type.orEmpty()
    )
}
