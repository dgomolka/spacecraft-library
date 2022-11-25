package com.psycodeinteractive.spacecraftlibrary.domain.coroutine

import kotlinx.coroutines.Dispatchers

object CoroutineContextProviderImpl : CoroutineContextProvider {
    override val main by lazy { Dispatchers.Main }
    override val io by lazy { Dispatchers.IO }
}
