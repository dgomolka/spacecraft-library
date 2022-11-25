package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AgencyDatabaseModel(
    @PrimaryKey
    val id: Int,
    val url: String,
    val name: String,
    val type: String
)
