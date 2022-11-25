package com.psycodeinteractive.spacecraftlibrary.presentation.execution

import com.psycodeinteractive.spacecraftlibrary.domain.execution.UseCaseExecutor
import kotlinx.coroutines.CoroutineScope

typealias UseCaseExecutorProvider = @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor
