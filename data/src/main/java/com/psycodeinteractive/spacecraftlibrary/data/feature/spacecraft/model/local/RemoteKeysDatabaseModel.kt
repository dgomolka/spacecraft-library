package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysDatabaseModel(
    @PrimaryKey
    val itemId: Int,
    val nextKey: String?
)
