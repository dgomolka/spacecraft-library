package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.AgencyDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.AgencyDatabaseModel

class AgencyDatabaseToDataMapper : DatabaseToDataMapper<AgencyDatabaseModel, AgencyDataModel>() {
    override fun map(input: AgencyDatabaseModel) = when (input.id) {
        -1 -> AgencyDataModel.Unspecified
        else -> AgencyDataModel.AgencyInfoDataModel(
            id = input.id,
            url = input.url,
            name = input.name,
            type = input.type
        )
    }
}
