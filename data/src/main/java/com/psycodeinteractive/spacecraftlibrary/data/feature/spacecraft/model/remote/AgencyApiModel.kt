package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class AgencyApiModel(
    val id: Int,
    val url: String,
    val name: String,
    val type: String?
)
