package com.psycodeinteractive.spacecraftlibrary.data.di

import android.content.Context
import androidx.room.Room
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.AgencyDataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftApiToDataMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftConfigDataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftDataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.StatusDataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.TypeDataToDatabaseMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.SpacecraftCache
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.SpacecraftRemoteMediator
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.local.AppDatabase
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.remote.SpacecraftApiService
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database"
    ).build()

    @Singleton
    @Provides
    fun providesSpacecraftRemoteMediator(
        appDatabase: AppDatabase,
        spacecraftApiService: SpacecraftApiService,
        spacecraftApiToDataMapper: SpacecraftApiToDataMapper,
        spacecraftDataToDatabaseMapper: SpacecraftDataToDatabaseMapper,
        cache: SpacecraftCache,
        logger: Logger
    ) = SpacecraftRemoteMediator(
        appDatabase,
        spacecraftApiService,
        spacecraftApiToDataMapper,
        spacecraftDataToDatabaseMapper,
        cache,
        logger
    )

    @Reusable
    @Provides
    fun providesSpacecraftDataToDatabaseMapper(
        statusDataToDatabaseMapper: StatusDataToDatabaseMapper,
        spacecraftConfigDataToDatabaseMapper: SpacecraftConfigDataToDatabaseMapper
    ) = SpacecraftDataToDatabaseMapper(
        statusDataToDatabaseMapper,
        spacecraftConfigDataToDatabaseMapper
    )

    @Reusable
    @Provides
    fun providesStatusDataToDatabaseMapper() = StatusDataToDatabaseMapper()

    @Reusable
    @Provides
    fun providesTypeDataToDatabaseMapper() = TypeDataToDatabaseMapper()

    @Reusable
    @Provides
    fun providesAgencyDataToDatabaseMapper() = AgencyDataToDatabaseMapper()

    @Reusable
    @Provides
    fun providesSpacecraftConfigApiToDataMapper(
        typeDataToDatabaseMapper: TypeDataToDatabaseMapper,
        agencyDataToDatabaseMapper: AgencyDataToDatabaseMapper
    ) = SpacecraftConfigDataToDatabaseMapper(
        typeDataToDatabaseMapper,
        agencyDataToDatabaseMapper
    )
}
