package com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase

interface Cancellable {
    fun cancel()
}

interface RunningExecution : Cancellable {
    fun isRunning(): Boolean
}
