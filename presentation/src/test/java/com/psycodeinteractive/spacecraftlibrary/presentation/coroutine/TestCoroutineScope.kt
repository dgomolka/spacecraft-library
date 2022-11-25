package com.psycodeinteractive.spacecraftlibrary.presentation.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
object TestCoroutineScope {
    fun createTestScope() = TestScope().apply {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
    }
}
