package com.psycodeinteractive.spacecraftlibrary.domain.di

import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.domain.logger.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {
    @Singleton
    @Provides
    fun providesLogger(): Logger = LoggerImpl()
}
