package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.remote

import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.model.remote.SpacecraftListResponseApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SpacecraftApiService {
    @GET("spacecraft")
    suspend fun getSpacecraftList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): SpacecraftListResponseApiModel
}
