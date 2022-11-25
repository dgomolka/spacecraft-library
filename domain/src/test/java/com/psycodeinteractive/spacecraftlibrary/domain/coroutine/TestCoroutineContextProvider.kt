package com.psycodeinteractive.spacecraftlibrary.domain.coroutine

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
private class TestCoroutineContextProvider(
    override val main: CoroutineContext = TestCoroutineScope.createTestScope().coroutineContext,
    override val io: CoroutineContext = TestCoroutineScope.createTestScope().coroutineContext
) : CoroutineContextProvider

val testCoroutineContextProvider: CoroutineContextProvider =
    TestCoroutineContextProvider()
