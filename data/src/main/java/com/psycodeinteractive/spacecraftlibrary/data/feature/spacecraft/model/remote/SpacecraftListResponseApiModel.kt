package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class SpacecraftListResponseApiModel(
    val results: List<SpacecraftApiModel>,
    val next: String?,
    val previous: String?
)
