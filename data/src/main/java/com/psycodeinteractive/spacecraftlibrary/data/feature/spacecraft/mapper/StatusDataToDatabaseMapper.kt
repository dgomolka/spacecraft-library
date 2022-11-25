package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.StatusDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.StatusDatabaseModel

class StatusDataToDatabaseMapper : DataToDatabaseMapper<StatusDataModel, StatusDatabaseModel>() {
    override fun map(input: StatusDataModel) = StatusDatabaseModel(
        id = input.id,
        name = input.name
    )
}
