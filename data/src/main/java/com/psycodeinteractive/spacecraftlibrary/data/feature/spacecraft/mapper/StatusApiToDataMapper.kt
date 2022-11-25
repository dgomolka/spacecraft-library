package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.ApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.StatusDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote.StatusApiModel

class StatusApiToDataMapper : ApiToDataMapper<StatusApiModel, StatusDataModel>() {
    override fun map(input: StatusApiModel) = StatusDataModel(
        id = input.id,
        name = input.name
    )
}
