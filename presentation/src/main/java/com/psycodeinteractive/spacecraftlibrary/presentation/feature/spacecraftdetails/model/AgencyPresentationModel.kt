package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model

sealed class AgencyPresentationModel {
    data class AgencyInfoPresentationModel(
        val id: Int,
        val url: String,
        val name: String,
        val type: String
    ) : AgencyPresentationModel()
    object Unspecified : AgencyPresentationModel()
}
