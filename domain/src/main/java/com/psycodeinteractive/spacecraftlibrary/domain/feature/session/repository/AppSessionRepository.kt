package com.psycodeinteractive.spacecraftlibrary.domain.feature.session.repository

interface AppSessionRepository {
    suspend fun startSession()
    suspend fun stopSession()
    suspend fun clearSession()
}
