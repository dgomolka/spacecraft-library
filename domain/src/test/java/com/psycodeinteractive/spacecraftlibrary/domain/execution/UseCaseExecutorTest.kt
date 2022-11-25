package com.psycodeinteractive.spacecraftlibrary.domain.execution

import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.TestCoroutineScope
import com.psycodeinteractive.spacecraftlibrary.domain.execution.usecase.BaseUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

typealias MockCallback = (String) -> Unit
typealias TestUseCase = BaseUseCase<Unit, String>

@OptIn(ExperimentalCoroutinesApi::class)
class UseCaseExecutorTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    lateinit var logger: Logger

    @MockK
    lateinit var testUseCase: TestUseCase

    @MockK
    lateinit var mockCallback: MockCallback

    private lateinit var testScope: TestScope

    private lateinit var classUnderTest: UseCaseExecutorImpl

    @Before
    fun setup() {
        testScope = TestCoroutineScope.createTestScope()

        classUnderTest = UseCaseExecutorImpl(testScope, logger)
    }

    @Test
    fun `Given successful use case execution invoke callback`() {
        testScope.runTest {
            // Given
            val expectedCallbackResult = ""
            every { mockCallback(any()) } just runs
            coEvery { testUseCase.launchExecution(Unit, mockCallback) } answers {
                secondArg<MockCallback>().invoke("")
            }

            // When
            classUnderTest.execute(testUseCase, mockCallback)
            advanceUntilIdle()

            // Then
            coVerify(exactly = 1) {
                testUseCase.launchExecution(Unit, mockCallback)
            }
            verify(exactly = 1) {
                mockCallback.invoke(expectedCallbackResult)
            }
        }
    }
}
