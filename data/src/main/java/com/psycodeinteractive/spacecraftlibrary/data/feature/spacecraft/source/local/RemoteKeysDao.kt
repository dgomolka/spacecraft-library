package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.RemoteKeysDatabaseModel

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertOrReplace(remoteKey: List<RemoteKeysDatabaseModel>)

    @Query("SELECT * FROM remote_keys WHERE :itemId = itemId")
    suspend fun remoteKeysByLoadedOffset(itemId: Int): RemoteKeysDatabaseModel?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()
}
