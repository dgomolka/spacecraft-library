package com.psycodeinteractive.spacecraftlibrary.presentation.di

import com.psycodeinteractive.spacecraftlibrary.domain.execution.UseCaseExecutorImpl
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.presentation.execution.UseCaseExecutorProvider
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper.AgencyDomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper.SpacecraftConfigDomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper.SpacecraftDomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper.StatusDomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper.TypeDomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.mapper.DefaultDomainToPresentationExceptionMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Singleton
    @Provides
    fun providesUseCaseExecutorProvider(logger: Logger): UseCaseExecutorProvider =
        { coroutineScope -> UseCaseExecutorImpl(coroutineScope, logger) }

    @Reusable
    @Provides
    fun providesSpacecraftDomainToPresentationMapper(
        statusDomainToPresentationMapper: StatusDomainToPresentationMapper,
        spacecraftConfigDomainToPresentationMapper: SpacecraftConfigDomainToPresentationMapper
    ) = SpacecraftDomainToPresentationMapper(
        statusDomainToPresentationMapper,
        spacecraftConfigDomainToPresentationMapper
    )

    @Reusable
    @Provides
    fun providesStatusDomainToPresentationMapper() = StatusDomainToPresentationMapper()

    @Reusable
    @Provides
    fun providesTypeDomainToPresentationMapper() = TypeDomainToPresentationMapper()

    @Reusable
    @Provides
    fun providesAgencyDomainToPresentationMapper() = AgencyDomainToPresentationMapper()

    @Reusable
    @Provides
    fun providesSpacecraftConfigDomainToPresentationMapper(
        typeDataToDatabaseMapper: TypeDomainToPresentationMapper,
        agencyDataToDatabaseMapper: AgencyDomainToPresentationMapper
    ) = SpacecraftConfigDomainToPresentationMapper(
        typeDataToDatabaseMapper,
        agencyDataToDatabaseMapper
    )

    @Reusable
    @Provides
    fun providesDefaultDomainToPresentationExceptionMapper() = DefaultDomainToPresentationExceptionMapper()
}
