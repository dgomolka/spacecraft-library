package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper

import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.StatusDataModel
import com.psycodeinteractive.spacecraftlibrary.domain.contract.mapper.DataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.StatusDomainModel

class StatusDataToDomainMapper : DataToDomainMapper<StatusDataModel, StatusDomainModel>() {
    override fun map(input: StatusDataModel) = StatusDomainModel(
        id = input.id,
        name = input.name
    )
}
