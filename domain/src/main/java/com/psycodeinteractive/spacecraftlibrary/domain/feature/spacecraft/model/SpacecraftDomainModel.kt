package com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model

data class SpacecraftDomainModel(
    val id: Int,
    val url: String,
    val name: String,
    val serialNumber: String,
    val status: StatusDomainModel,
    val description: String,
    val spacecraftConfig: SpacecraftConfigDomainModel
)
