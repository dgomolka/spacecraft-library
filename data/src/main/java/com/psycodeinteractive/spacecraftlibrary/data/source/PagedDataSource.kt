package com.psycodeinteractive.spacecraftlibrary.data.source

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PagedDataSource<DataModel : Any> : DataSource<PagingData<DataModel>> {
    suspend fun snapshot(): Flow<List<DataModel>>
}
