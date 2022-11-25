package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.AgencyDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.AgencyDataModel.AgencyInfoDataModel
import com.psycodeinteractive.spacecraftlibrary.domain.contract.mapper.DataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.AgencyDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.AgencyDomainModel.AgencyInfoDomainModel

class AgencyDataToDomainMapper : DataToDomainMapper<AgencyDataModel, AgencyDomainModel>() {
    override fun map(input: AgencyDataModel) = when (input) {
        is AgencyInfoDataModel -> AgencyInfoDomainModel(
            id = input.id,
            url = input.url,
            name = input.name,
            type = input.type
        )
        AgencyDataModel.Unspecified -> AgencyDomainModel.Unspecified
    }
}
