package com.psycodeinteractive.spacecraftlibrary.domain.execution

import com.psycodeinteractive.spacecraftlibrary.domain.model.exception.DomainException
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BaseUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.RunningExecution
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UseCaseExecutorImpl(
    private var coroutineScope: CoroutineScope,
    private var logger: Logger
) : UseCaseExecutor {
    override fun <Output> execute(
        useCase: BaseUseCase<Unit, Output>,
        callback: (Output) -> Unit,
        onError: (DomainException) -> Unit
    ) = execute(useCase, Unit, callback, onError)

    override fun <Input, Output> execute(
        useCase: BaseUseCase<Input, Output>,
        value: Input,
        callback: (Output) -> Unit,
        onError: (DomainException) -> Unit
    ): RunningExecution {
        val job = launchUseCaseExecution(useCase, value, callback, onError)
        return RunningUseCaseExecution(job)
    }

    private fun <Input, Output> launchUseCaseExecution(
        useCase: BaseUseCase<Input, Output>,
        value: Input,
        callback: (Output) -> Unit,
        onError: (DomainException) -> Unit
    ) = coroutineScope.launch {
        logger.d("Executing UseCase: ${useCase::class.simpleName} with value: $value")

        try {
            useCase.launchExecution(value, callback)
        } catch (cancellationException: CancellationException) {
            cancellationException.printStackTrace()
            logger.e(cancellationException.cause ?: cancellationException)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            logger.d(throwable)
            onError(throwable as? DomainException ?: useCase.onError(throwable))
        }
    }

    class RunningUseCaseExecution(private val job: Job) : RunningExecution {
        override fun isRunning() = job.isActive

        override fun cancel() {
            job.cancel()
        }
    }
}
