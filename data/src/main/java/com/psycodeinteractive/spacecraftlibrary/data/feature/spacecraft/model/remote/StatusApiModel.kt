package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class StatusApiModel(
    val id: Int,
    val name: String
)
