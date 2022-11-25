package com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model

sealed class AgencyDomainModel {
    data class AgencyInfoDomainModel(
        val id: Int,
        val url: String,
        val name: String,
        val type: String
    ) : AgencyDomainModel()
    object Unspecified : AgencyDomainModel()
}
