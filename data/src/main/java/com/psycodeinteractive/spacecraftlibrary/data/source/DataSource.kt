package com.psycodeinteractive.spacecraftlibrary.data.source

import kotlinx.coroutines.flow.Flow

interface DataSource<DataModel> {
    suspend fun initialize()
    suspend fun refresh()
    suspend fun get(): Flow<DataModel>
}
