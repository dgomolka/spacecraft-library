package com.psycodeinteractive.spacecraftlibrary.data.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Cache<State> {
    private val sharedFlow = MutableSharedFlow<State>(replay = 1)
    val flow: Flow<State> get() = sharedFlow
    private val mutex = Mutex()

    suspend fun emitIfEmpty(ifEmpty: suspend () -> State): Cache<State> {
        with(sharedFlow) {
            if (replayCache.isNotEmpty()) return this@Cache
            mutex.withLock {
                val newIfEmpty = ifEmpty()
                if (newIfEmpty is Collection<*> && newIfEmpty.isEmpty()) return@withLock
                emit(ifEmpty())
            }
        }
        return this
    }

    suspend fun emit(update: suspend (lastState: State?) -> State) {
        mutex.withLock {
            with(sharedFlow) {
                val state = if (replayCache.isNotEmpty()) first() else null
                emit(update(state))
            }
        }
    }

    suspend fun clear() {
        mutex.withLock {
            sharedFlow.resetReplayCache()
        }
    }
}
