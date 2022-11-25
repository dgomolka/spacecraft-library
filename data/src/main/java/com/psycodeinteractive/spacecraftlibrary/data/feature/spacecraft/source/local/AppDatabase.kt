package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.AgencyDatabaseModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.RemoteKeysDatabaseModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftConfigDatabaseModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftDatabaseModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.StatusDatabaseModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.TypeDatabaseModel

@Database(
    entities = [
        StatusDatabaseModel::class,
        SpacecraftDatabaseModel::class,
        SpacecraftConfigDatabaseModel::class,
        TypeDatabaseModel::class,
        AgencyDatabaseModel::class,
        RemoteKeysDatabaseModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun spacecraftDao(): SpacecraftDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
