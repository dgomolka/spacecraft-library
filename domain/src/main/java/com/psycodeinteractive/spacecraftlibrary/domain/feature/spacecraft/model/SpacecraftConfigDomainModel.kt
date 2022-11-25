package com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model

data class SpacecraftConfigDomainModel(
    val id: Int,
    val url: String,
    val name: String,
    val type: TypeDomainModel,
    val agency: AgencyDomainModel,
    val isInUse: Boolean,
    val imageUrl: String
)
