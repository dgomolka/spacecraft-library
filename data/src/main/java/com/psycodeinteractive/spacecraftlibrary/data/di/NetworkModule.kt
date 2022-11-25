package com.psycodeinteractive.spacecraftlibrary.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.psycodeinteractive.spacecraftlibrary.data.BuildConfig
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.remote.SpacecraftApiService
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.remote.config.SpacecraftApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesSpacecraftApiConfig() = SpacecraftApiConfig

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesSpacecraftApiService(
        okHttpClient: OkHttpClient,
        apiConfig: SpacecraftApiConfig
    ): SpacecraftApiService = Retrofit.Builder()
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(apiConfig.baseUrl)
        .client(okHttpClient)
        .build()
        .create(SpacecraftApiService::class.java)

    @Singleton
    @Provides
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, SECONDS)
        .readTimeout(30, SECONDS)
        .writeTimeout(30, SECONDS)
        .apply {
            when {
                BuildConfig.DEBUG -> {
                    val logging = HttpLoggingInterceptor()
                    logging.level = BODY
                    addInterceptor(logging)
                }
            }
        }
        .build()
}
