package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.mapper.DataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.AgencyDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.AgencyDatabaseModel

class AgencyDataToDatabaseMapper : DataToDatabaseMapper<AgencyDataModel, AgencyDatabaseModel>() {
    override fun map(input: AgencyDataModel) = when (input) {
        is AgencyDataModel.AgencyInfoDataModel -> AgencyDatabaseModel(
            id = input.id,
            url = input.url,
            name = input.name,
            type = input.type
        )
        AgencyDataModel.Unspecified -> AgencyDatabaseModel(
            id = -1,
            url = "",
            name = "",
            type = ""
        )
    }
}
