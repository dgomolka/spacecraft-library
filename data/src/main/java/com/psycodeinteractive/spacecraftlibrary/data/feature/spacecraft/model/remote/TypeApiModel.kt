package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class TypeApiModel(
    val id: Int,
    val name: String
)
