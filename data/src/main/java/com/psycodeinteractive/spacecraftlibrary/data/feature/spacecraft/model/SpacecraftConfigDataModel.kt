package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model

data class SpacecraftConfigDataModel(
    val id: Int,
    val url: String,
    val name: String,
    val type: TypeDataModel,
    val agency: AgencyDataModel,
    val isInUse: Boolean,
    val imageUrl: String
)
