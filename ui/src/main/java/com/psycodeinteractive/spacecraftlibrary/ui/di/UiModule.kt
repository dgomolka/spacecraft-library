package com.psycodeinteractive.spacecraftlibrary.ui.di

import android.content.Context
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper.AgencyPresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper.SpacecraftConfigPresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper.SpacecraftPresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper.StatusPresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper.TypePresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.mapper.exception.DefaultPresentationToUiExceptionMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Reusable
    @Provides
    fun providesSpacecraftPresentationToUiMapper(
        statusPresentationToUiMapper: StatusPresentationToUiMapper,
        spacecraftConfigPresentationToUiMapper: SpacecraftConfigPresentationToUiMapper
    ) = SpacecraftPresentationToUiMapper(
        statusPresentationToUiMapper,
        spacecraftConfigPresentationToUiMapper
    )

    @Reusable
    @Provides
    fun providesStatusPresentationToUiMapper() = StatusPresentationToUiMapper()

    @Reusable
    @Provides
    fun providesTypePresentationToUiMapper() = TypePresentationToUiMapper()

    @Reusable
    @Provides
    fun providesAgencyPresentationToUiMapper() = AgencyPresentationToUiMapper()

    @Reusable
    @Provides
    fun providesDefaultPresentationToUiExceptionMapper() = DefaultPresentationToUiExceptionMapper()

    @Reusable
    @Provides
    fun providesSpacecraftConfigPresentationToUiMapper(
        typePresentationToUiMapper: TypePresentationToUiMapper,
        agencyPresentationToUiMapper: AgencyPresentationToUiMapper
    ) = SpacecraftConfigPresentationToUiMapper(
        typePresentationToUiMapper,
        agencyPresentationToUiMapper
    )

    @Provides
    @Singleton
    fun providesImageLoaderFactory(
        @ApplicationContext context: Context
    ) = ImageLoaderFactory {
        ImageLoader.Builder(context)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.4)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.1)
                    .build()
            }
            .crossfade(true)
            .build()
    }.apply {
        Coil.setImageLoader(this)
    }
}
