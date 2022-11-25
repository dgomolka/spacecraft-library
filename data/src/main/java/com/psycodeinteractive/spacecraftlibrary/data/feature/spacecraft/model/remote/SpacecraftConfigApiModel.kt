package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpacecraftConfigApiModel(
    val id: Int,
    val url: String,
    val name: String,
    val type: TypeApiModel,
    val agency: AgencyApiModel?,
    @SerialName("in_use")
    val isInUse: Boolean,
    @SerialName("image_url")
    val imageUrl: String?
)
