package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model

data class SpacecraftUiModel(
    val id: Int,
    val url: String,
    val name: String,
    val serialNumber: String,
    val status: StatusUiModel,
    val description: String,
    val spacecraftConfig: SpacecraftConfigUiModel
)
