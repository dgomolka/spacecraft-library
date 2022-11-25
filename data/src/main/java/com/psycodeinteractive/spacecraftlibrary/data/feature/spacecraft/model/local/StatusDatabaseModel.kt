package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatusDatabaseModel(
    @PrimaryKey
    val id: Int,
    val name: String
)
