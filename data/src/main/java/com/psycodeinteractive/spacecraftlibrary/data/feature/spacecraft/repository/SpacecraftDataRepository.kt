package com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.repository

import androidx.paging.map
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.mapper.SpacecraftDataToDomainMapper
import com.psycodeinteractive.spacecraftlibrary.data.feature.spacecraft.source.SpacecraftDataSource
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.repository.SpacecraftRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SpacecraftDataRepository(
    private val spacecraftDataSource: SpacecraftDataSource,
    private val spacecraftDataToDomainMapper: SpacecraftDataToDomainMapper
) : SpacecraftRepository {
    override suspend fun getSpacecraftList() = spacecraftDataSource.get().map { pagingData ->
        pagingData.map(spacecraftDataToDomainMapper::toDomain)
    }

    override suspend fun getSpacecraft(id: Int) = spacecraftDataToDomainMapper.toDomain(
        spacecraftDataSource.snapshot().first().first { it.id == id }
    )
}
