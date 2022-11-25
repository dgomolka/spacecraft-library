package com.psycodeinteractive.spacecraftlibrary.domain.coroutine

import com.psycodeinteractive.spacecraftlibrary.presentation.coroutine.TestCoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
private class TestCoroutineContextProvider(
    override val main: CoroutineContext = TestCoroutineScope.createTestScope().coroutineContext,
    override val io: CoroutineContext = TestCoroutineScope.createTestScope().coroutineContext
) : CoroutineContextProvider

val testCoroutineContextProvider: CoroutineContextProvider =
    TestCoroutineContextProvider()
