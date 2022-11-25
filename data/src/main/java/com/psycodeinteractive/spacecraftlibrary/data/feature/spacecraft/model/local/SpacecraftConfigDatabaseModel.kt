package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpacecraftConfigDatabaseModel(
    @PrimaryKey
    val id: Int,
    val url: String,
    val name: String,
    @Embedded(prefix = "spacecraft_config_embedded_type_")
    val type: TypeDatabaseModel,
    @Embedded(prefix = "spacecraft_config_embedded_agency_")
    val agency: AgencyDatabaseModel,
    @ColumnInfo(name = "in_use")
    val isInUse: Boolean,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)
