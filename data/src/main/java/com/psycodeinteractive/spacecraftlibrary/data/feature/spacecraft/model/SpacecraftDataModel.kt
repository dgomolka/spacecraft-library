package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model

data class SpacecraftDataModel(
    val id: Int,
    val url: String,
    val name: String,
    val serialNumber: String,
    val status: StatusDataModel,
    val description: String,
    val spacecraftConfig: SpacecraftConfigDataModel
)
