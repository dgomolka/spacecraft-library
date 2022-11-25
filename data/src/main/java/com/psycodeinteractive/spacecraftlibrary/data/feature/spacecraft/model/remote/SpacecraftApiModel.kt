package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpacecraftApiModel(
    val id: Int,
    val url: String,
    val name: String,
    @SerialName("serial_number")
    val serialNumber: String?,
    val status: StatusApiModel,
    val description: String,
    @SerialName("spacecraft_config")
    val spacecraftConfig: SpacecraftConfigApiModel
)
