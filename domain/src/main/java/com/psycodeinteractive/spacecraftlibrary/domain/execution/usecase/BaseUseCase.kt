package com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase

import com.psycodeinteractive.spacecraftlibrary.domain.model.exception.DomainException
import com.psycodeinteractive.spacecraftlibrary.domain.model.exception.UnknownDomainException

interface BaseUseCase<Input, Output> {
    suspend fun launchExecution(value: Input, callback: (Output) -> Unit)

    fun onError(throwable: Throwable): DomainException = UnknownDomainException(throwable)
}
