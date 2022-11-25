package com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.repository

import androidx.paging.PagingData
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import kotlinx.coroutines.flow.Flow

interface SpacecraftRepository {
    suspend fun getSpacecraftList(): Flow<PagingData<SpacecraftDomainModel>>
    suspend fun getSpacecraft(id: Int): SpacecraftDomainModel
}
