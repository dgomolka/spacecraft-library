package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model

data class SpacecraftConfigPresentationModel(
    val id: Int,
    val url: String,
    val name: String,
    val type: TypePresentationModel,
    val agency: AgencyPresentationModel,
    val isInUse: Boolean,
    val imageUrl: String
)
