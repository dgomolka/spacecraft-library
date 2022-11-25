package com.psycodeinteractive.spacecraftlibrary.domain.execution

import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BaseUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.RunningExecution
import com.psycodeinteractive.spacecraftlibrary.domain.model.exception.DomainException

interface UseCaseExecutor {
    fun <Output> execute(
        useCase: BaseUseCase<Unit, Output>,
        callback: (Output) -> Unit = {},
        onError: (DomainException) -> Unit = {}
    ): RunningExecution

    fun <Input, Output> execute(
        useCase: BaseUseCase<Input, Output>,
        value: Input,
        callback: (Output) -> Unit = {},
        onError: (DomainException) -> Unit = {}
    ): RunningExecution
}
