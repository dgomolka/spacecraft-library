package com.psycodeinteractive.spacecraftlibrary.presentation.extension

import com.psycodeinteractive.spacecraftlibrary.domain.execution.UseCaseExecutor
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BaseUseCase
import io.mockk.every
import io.mockk.mockk

inline fun <reified Request : Any, reified Result : Any> UseCaseExecutor.givenSuccessfulUseCaseExecution(
    useCase: BaseUseCase<Request, Result>,
    request: Request,
    result: Result
) {
    every { execute(useCase = useCase, callback = any(), value = request, onError = any()) } answers {
        secondArg<(Result) -> Unit>().invoke(result)
        mockk()
    }
}

inline fun <reified Result : Any> UseCaseExecutor.givenSuccessfulNoArgumentUseCaseExecution(
    useCase: BaseUseCase<Unit, Result>,
    result: Result
) {
    every { execute(useCase = useCase, callback = any(), onError = any()) } answers {
        secondArg<(Result) -> Unit>().invoke(result)
        mockk()
    }
}

internal fun UseCaseExecutor.givenSuccessfulNoArgumentNoResultUseCaseExecution(
    useCase: BaseUseCase<Unit, Unit>
) {
    givenSuccessfulNoArgumentUseCaseExecution(useCase, Unit)
}
