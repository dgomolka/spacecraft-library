package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spacecraft_table")
data class SpacecraftDatabaseModel(
    @PrimaryKey val id: Int,
    val url: String,
    val name: String,
    @ColumnInfo(name = "serial_number")
    val serialNumber: String,
    @Embedded(prefix = "spacecraft_embedded_status_")
    val status: StatusDatabaseModel,
    val description: String,
    @Embedded(prefix = "spacecraft_embedded_spacecraft_config")
    val spacecraftConfig: SpacecraftConfigDatabaseModel
)
