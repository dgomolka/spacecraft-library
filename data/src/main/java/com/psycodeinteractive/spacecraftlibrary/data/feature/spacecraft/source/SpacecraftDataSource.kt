package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source

import androidx.paging.Pager
import androidx.paging.PagingSource
import androidx.paging.map
import com.psycodeinteractive.spacecraftlibrary.data.cache.Cache
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftDatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftDataModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftPagingContainer
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftDatabaseModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.local.SpacecraftDao
import com.psycodeinteractive.spacecraftlibrary.data.source.PagedDataSource
import kotlinx.coroutines.flow.map

typealias SpacecraftCache = Cache<List<SpacecraftDataModel>>
typealias SpacecraftPager = Pager<Int, SpacecraftDatabaseModel>
typealias SpacecraftPagingSource = PagingSource<Int, SpacecraftDatabaseModel>

class SpacecraftDataSource(
    private val spacecraftDao: SpacecraftDao,
    private val spacecraftDatabaseToDataMapper: SpacecraftDatabaseToDataMapper,
    private val spacecraftPagingContainer: SpacecraftPagingContainer,
    private val cache: SpacecraftCache
) : PagedDataSource<SpacecraftDataModel> {

    override suspend fun initialize() {
        cache.emitIfEmpty {
            fetchLocal()
        }
    }

    override suspend fun refresh() {
        spacecraftPagingContainer.spacecraftPagingSource.invalidate()
    }

    override suspend fun get() = spacecraftPagingContainer.spacecraftPager.flow.map { pagingData ->
        pagingData.map(spacecraftDatabaseToDataMapper::toData)
    }

    override suspend fun snapshot() = cache.flow

    private suspend fun fetchLocal() = spacecraftDao.getAll()
        .map(spacecraftDatabaseToDataMapper::toData)
}
