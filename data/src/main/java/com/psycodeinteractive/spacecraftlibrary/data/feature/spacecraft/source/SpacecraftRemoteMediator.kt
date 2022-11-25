package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.APPEND
import androidx.paging.LoadType.PREPEND
import androidx.paging.LoadType.REFRESH
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
import androidx.paging.RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH
import androidx.paging.RemoteMediator.MediatorResult.Error
import androidx.paging.RemoteMediator.MediatorResult.Success
import androidx.room.withTransaction
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftDataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.RemoteKeysDatabaseModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.local.SpacecraftDatabaseModel
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.local.AppDatabase
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.remote.SpacecraftApiService
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception

const val SPACECRAFT_PAGE_SIZE = 100

@OptIn(ExperimentalPagingApi::class)
class SpacecraftRemoteMediator(
    private val appDatabase: AppDatabase,
    private val spacecraftApiService: SpacecraftApiService,
    private val spacecraftApiToDataMapper: SpacecraftApiToDataMapper,
    private val spacecraftDataToDatabaseMapper: SpacecraftDataToDatabaseMapper,
    private val cache: SpacecraftCache,
    private val logger: Logger
) : RemoteMediator<Int, SpacecraftDatabaseModel>() {
    private var spacecraftPagingSource: PagingSource<Int, SpacecraftDatabaseModel>? = null
    private val spacecraftDao = appDatabase.spacecraftDao()
    private val remoteKeysDao = appDatabase.remoteKeysDao()

    override suspend fun initialize() = if (spacecraftDao.count() > 0) {
        SKIP_INITIAL_REFRESH
    } else {
        LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SpacecraftDatabaseModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                REFRESH -> 0
                PREPEND -> return Success(endOfPaginationReached = true)
                APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)

                    remoteKeys?.nextKey ?: return Success(endOfPaginationReached = remoteKeys != null)
                    spacecraftDao.count()
                }
            }

            val response = spacecraftApiService.getSpacecraftList(offset = loadKey, limit = SPACECRAFT_PAGE_SIZE)

            appDatabase.withTransaction {
                if (loadType == REFRESH) {
                    cache.clear()
                    spacecraftDao.deleteAll()
                    remoteKeysDao.deleteAll()
                }

                val spacecraftDataList = response.results.map(spacecraftApiToDataMapper::toData)

                remoteKeysDao.insertOrReplace(
                    spacecraftDataList.map { RemoteKeysDatabaseModel(it.id, response.next) }
                )

                spacecraftDao.insertAll(
                    spacecraftDataList.map(spacecraftDataToDatabaseMapper::toDatabase)
                )

                cache.emit { lastState ->
                    lastState.orEmpty() + spacecraftDataList
                }
            }

            spacecraftPagingSource?.invalidate()

            Success(endOfPaginationReached = response.next == null)
        } catch (e: Exception) {
            logger.e(e)
            when (e) {
                is IOException, is HttpException -> Error(e)
                else -> throw e
            }
        }
    }

    fun attachPagingSource(spacecraftPagingSource: SpacecraftPagingSource) {
        this.spacecraftPagingSource = spacecraftPagingSource
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, SpacecraftDatabaseModel>): RemoteKeysDatabaseModel? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { item ->
            val remoteKey = remoteKeysDao.remoteKeysByLoadedOffset(item.id)
            remoteKey
        }
    }
}
