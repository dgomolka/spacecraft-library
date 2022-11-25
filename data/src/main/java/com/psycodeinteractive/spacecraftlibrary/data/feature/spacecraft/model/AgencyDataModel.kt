package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model

sealed class AgencyDataModel {
    data class AgencyInfoDataModel(
        val id: Int,
        val url: String,
        val name: String,
        val type: String
    ) : AgencyDataModel()
    object Unspecified : AgencyDataModel()
}
