package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftDatabaseModel

@Dao
interface SpacecraftDao {
    @Query("SELECT * FROM spacecraft_table")
    suspend fun getAll(): List<SpacecraftDatabaseModel>

    @Query("SELECT * FROM spacecraft_table")
    fun getAllPagingSource(): PagingSource<Int, SpacecraftDatabaseModel>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(spacecraftList: List<SpacecraftDatabaseModel>)

    @Query("DELETE FROM spacecraft_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM spacecraft_table")
    suspend fun count(): Int
}
