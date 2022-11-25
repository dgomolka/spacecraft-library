package com.psycodeinteractive.spacecraftlibrary.presentation.feature.launch

import com.psycodeinteractive.spacecraftlibrary.domain.execution.UseCaseExecutor
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StartSessionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StopSessionUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.presentation.extension.givenSuccessfulNoArgumentNoResultUseCaseExecution
import com.psycodeinteractive.spacecraftlibrary.presentation.mapper.DefaultDomainToPresentationExceptionMapper
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LaunchViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var useCaseExecutor: UseCaseExecutor

    @MockK
    lateinit var startSessionUseCase: StartSessionUseCase

    @MockK
    lateinit var stopSessionUseCase: StopSessionUseCase

    @MockK
    lateinit var defaultDomainToPresentationExceptionMapper: DefaultDomainToPresentationExceptionMapper

    @MockK
    lateinit var logger: Logger

    private lateinit var classUnderTest: LaunchViewModel

    @Before
    fun setup() {
        classUnderTest = LaunchViewModel(
            startSessionUseCase = startSessionUseCase,
            stopSessionUseCase = stopSessionUseCase,
            useCaseExecutorProvider = { useCaseExecutor },
            domainToPresentationExceptionMapper = defaultDomainToPresentationExceptionMapper,
            logger = logger
        )
    }

    @Test
    fun `Given successful use case execution when onStart then verify use case was executed`() {
        // Given
        useCaseExecutor.givenSuccessfulNoArgumentNoResultUseCaseExecution(startSessionUseCase)

        // When
        classUnderTest.onStart()

        // Then
        verify(exactly = 1) {
            useCaseExecutor.execute(
                startSessionUseCase,
                callback = any(),
                onError = any()
            )
        }
    }

    @Test
    fun `Given successful use case execution when onStop then verify use case was executed`() {
        // Given
        useCaseExecutor.givenSuccessfulNoArgumentNoResultUseCaseExecution(stopSessionUseCase)

        // When
        classUnderTest.onStop()

        // Then
        verify(exactly = 1) {
            useCaseExecutor.execute(
                stopSessionUseCase,
                callback = any(),
                onError = any()
            )
        }
    }
}
