package com.psycodeinteractive.spacecraftlibrary.domain.di

import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.CoroutineContextProvider
import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.CoroutineContextProviderImpl
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.repository.AppSessionRepository
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StartSessionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StartSessionUseCaseImpl
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StopSessionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StopSessionUseCaseImpl
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.repository.SpacecraftRepository
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase.GetSpacecraftByIdUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase.GetSpacecraftByIdUseCaseImpl
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase.GetSpacecraftListUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase.GetSpacecraftListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Reusable
    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider =
        CoroutineContextProviderImpl

    @Reusable
    @Provides
    fun providesStartSessionUseCase(
        appSessionRepository: AppSessionRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): StartSessionUseCase = StartSessionUseCaseImpl(appSessionRepository, coroutineContextProvider)

    @Reusable
    @Provides
    fun providesStopSessionUseCase(
        appSessionRepository: AppSessionRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): StopSessionUseCase = StopSessionUseCaseImpl(appSessionRepository, coroutineContextProvider)

    @Reusable
    @Provides
    fun providesGetSpacecraftListUseCase(
        spacecraftRepository: SpacecraftRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetSpacecraftListUseCase = GetSpacecraftListUseCaseImpl(spacecraftRepository, coroutineContextProvider)

    @Reusable
    @Provides
    fun providesGetSpacecraftByIdUseCase(
        spacecraftRepository: SpacecraftRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetSpacecraftByIdUseCase = GetSpacecraftByIdUseCaseImpl(spacecraftRepository, coroutineContextProvider)
}
