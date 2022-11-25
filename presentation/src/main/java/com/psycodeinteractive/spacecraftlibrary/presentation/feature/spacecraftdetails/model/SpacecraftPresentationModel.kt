package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model

data class SpacecraftPresentationModel(
    val id: Int,
    val url: String,
    val name: String,
    val serialNumber: String,
    val status: StatusPresentationModel,
    val description: String,
    val spacecraftConfig: SpacecraftConfigPresentationModel
)
