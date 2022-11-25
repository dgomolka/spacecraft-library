package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model

sealed class AgencyUiModel {
    data class AgencyInfoUiModel(
        val id: Int,
        val url: String,
        val name: String,
        val type: String
    ) : AgencyUiModel()
    object Unspecified : AgencyUiModel()
}
