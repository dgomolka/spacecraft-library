package com.psycodeinteractive.spacecraftlibrary.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import com.psycodeinteractive.spacecraftlibrary.data.feature.session.AppSessionDataRepository
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.AgencyApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.AgencyDataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.AgencyDatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftConfigApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftConfigDataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftConfigDatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftDataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftDatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.StatusApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.StatusDataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.StatusDatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.TypeApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.TypeDataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.TypeDatabaseToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.SpacecraftPagingContainer
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.repository.SpacecraftDataRepository
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.SPACECRAFT_PAGE_SIZE
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.SpacecraftCache
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.SpacecraftDataSource
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.SpacecraftPager
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.SpacecraftRemoteMediator
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.local.AppDatabase
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.local.SpacecraftDao
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.repository.AppSessionRepository
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.repository.SpacecraftRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val SPACECRAFT_PAGING_CONFIG = "SpacecraftPagingConfig"

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesSpacecraftDao(
        appDatabase: AppDatabase
    ) = appDatabase.spacecraftDao()

    @ExperimentalPagingApi
    @Singleton
    @Provides
    fun providesSpacecraftPagingContainer(
        @Named(SPACECRAFT_PAGING_CONFIG) config: PagingConfig,
        remoteMediator: SpacecraftRemoteMediator,
        spacecraftDao: SpacecraftDao
    ) = SpacecraftPagingContainer().apply {
        spacecraftPager = SpacecraftPager(
            config = config,
            remoteMediator = remoteMediator,
            pagingSourceFactory = {
                spacecraftDao.getAllPagingSource().apply {
                    spacecraftPagingSource = this
                    remoteMediator.attachPagingSource(this)
                }
            }
        )
    }

    @Reusable
    @Provides
    fun providesSpacecraftDataSource(
        spacecraftDao: SpacecraftDao,
        spacecraftDatabaseToDataMapper: SpacecraftDatabaseToDataMapper,
        spacecraftPagingContainer: SpacecraftPagingContainer,
        cache: SpacecraftCache
    ) = SpacecraftDataSource(
        spacecraftDao,
        spacecraftDatabaseToDataMapper,
        spacecraftPagingContainer,
        cache
    )

    @Named(SPACECRAFT_PAGING_CONFIG)
    @Reusable
    @Provides
    fun providesPagingConfig() = PagingConfig(
        pageSize = SPACECRAFT_PAGE_SIZE,
        initialLoadSize = SPACECRAFT_PAGE_SIZE,
        prefetchDistance = SPACECRAFT_PAGE_SIZE / 2
    )

    @Singleton
    @Provides
    fun providesSpacecraftCache() = SpacecraftCache()

    @Reusable
    @Provides
    fun providesSpacecraftApiToDataMapper(
        statusApiToDataMapper: StatusApiToDataMapper,
        spacecraftConfigApiToDataMapper: SpacecraftConfigApiToDataMapper
    ) = SpacecraftApiToDataMapper(
        statusApiToDataMapper,
        spacecraftConfigApiToDataMapper
    )

    @Reusable
    @Provides
    fun providesStatusApiToDataMapper() = StatusApiToDataMapper()

    @Reusable
    @Provides
    fun providesTypeApiToDataMapper() = TypeApiToDataMapper()

    @Reusable
    @Provides
    fun providesAgencyApiToDataMapper() = AgencyApiToDataMapper()

    @Reusable
    @Provides
    fun providesSpacecraftConfigApiToDataMapper(
        typeApiToDataMapper: TypeApiToDataMapper,
        agencyApiToDataMapper: AgencyApiToDataMapper
    ) = SpacecraftConfigApiToDataMapper(
        typeApiToDataMapper,
        agencyApiToDataMapper
    )

    @Reusable
    @Provides
    fun providesSpacecraftRepository(
        spacecraftDataSource: SpacecraftDataSource,
        spacecraftDataToDomainMapper: SpacecraftDataToDomainMapper
    ): SpacecraftRepository = SpacecraftDataRepository(
        spacecraftDataSource,
        spacecraftDataToDomainMapper
    )

    @Reusable
    @Provides
    fun providesAppSessionRepository(
        spacecraftDataSource: SpacecraftDataSource
    ): AppSessionRepository = AppSessionDataRepository(
        listOf(
            spacecraftDataSource
        )
    )

    @Reusable
    @Provides
    fun providesSpacecraftDatabaseToDataMapper(
        statusDatabaseToDataMapper: StatusDatabaseToDataMapper,
        spacecraftConfigDatabaseToDataMapper: SpacecraftConfigDatabaseToDataMapper
    ) = SpacecraftDatabaseToDataMapper(
        statusDatabaseToDataMapper,
        spacecraftConfigDatabaseToDataMapper
    )

    @Reusable
    @Provides
    fun providesStatusDatabaseToDataMapper() = StatusDatabaseToDataMapper()

    @Reusable
    @Provides
    fun providesTypeDatabaseToDataMapper() = TypeDatabaseToDataMapper()

    @Reusable
    @Provides
    fun providesAgencyDatabaseToDataMapper() = AgencyDatabaseToDataMapper()

    @Reusable
    @Provides
    fun providesSpacecraftConfigDatabaseToDataMapper(
        typeDatabaseToDataMapper: TypeDatabaseToDataMapper,
        agencyDatabaseToDataMapper: AgencyDatabaseToDataMapper
    ) = SpacecraftConfigDatabaseToDataMapper(
        typeDatabaseToDataMapper,
        agencyDatabaseToDataMapper
    )

    @Reusable
    @Provides
    fun providesSpacecraftDataToDomainMapper(
        statusDataToDomainMapper: StatusDataToDomainMapper,
        spacecraftConfigDataToDomainMapper: SpacecraftConfigDataToDomainMapper
    ) = SpacecraftDataToDomainMapper(
        statusDataToDomainMapper,
        spacecraftConfigDataToDomainMapper
    )

    @Reusable
    @Provides
    fun providesStatusDataToDomainMapper() = StatusDataToDomainMapper()

    @Reusable
    @Provides
    fun providesTypeDataToDomainMapper() = TypeDataToDomainMapper()

    @Reusable
    @Provides
    fun providesAgencyDataToDomainMapper() = AgencyDataToDomainMapper()

    @Reusable
    @Provides
    fun providesSpacecraftConfigDataToDomainMapper(
        typeDataToDomainMapper: TypeDataToDomainMapper,
        agencyDataToDomainMapper: AgencyDataToDomainMapper
    ) = SpacecraftConfigDataToDomainMapper(
        typeDataToDomainMapper,
        agencyDataToDomainMapper
    )
}
