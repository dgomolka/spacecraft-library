package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.StatusDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.StatusDatabaseModel

class StatusDatabaseToDataMapper : DatabaseToDataMapper<StatusDatabaseModel, StatusDataModel>() {
    override fun map(input: StatusDatabaseModel) = StatusDataModel(
        id = input.id,
        name = input.name
    )
}
