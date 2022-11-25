package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model

data class SpacecraftConfigUiModel(
    val id: Int,
    val url: String,
    val name: String,
    val type: TypeUiModel,
    val agency: AgencyUiModel,
    val isInUse: Boolean,
    val imageUrl: String
)
