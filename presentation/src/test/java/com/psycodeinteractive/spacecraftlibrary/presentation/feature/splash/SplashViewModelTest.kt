package com.psycodeinteractive.spacecraftlibrary.presentation.feature.splash

import com.psycodeinteractive.spacecraftlibrary.domain.execution.UseCaseExecutor
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.presentation.coroutine.TestCoroutineScope
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftlist.SpacecraftListPresentationDestination
import com.psycodeinteractive.spacecraftlibrary.presentation.mapper.DefaultDomainToPresentationExceptionMapper
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var useCaseExecutor: UseCaseExecutor

    @MockK
    lateinit var defaultDomainToPresentationExceptionMapper: DefaultDomainToPresentationExceptionMapper

    @MockK
    lateinit var logger: Logger

    private lateinit var testScope: TestScope

    private lateinit var classUnderTest: SplashViewModel

    @Before
    fun setup() {
        testScope = TestCoroutineScope.createTestScope()
        classUnderTest = SplashViewModel(
            useCaseExecutorProvider = { useCaseExecutor },
            domainToPresentationExceptionMapper = defaultDomainToPresentationExceptionMapper,
            logger = logger
        )
    }

    @Test
    fun `When onViewCreated then verify StartSplash event was sent`() = testScope.runTest {
        // Given
        val expectedEvent = SplashEvent.StartSplash

        // When
        classUnderTest.onViewCreated()

        // Then
        val actualEvent = classUnderTest.eventFlow.first()
        assertEquals(expectedEvent, actualEvent)
    }

    @Test
    fun `When onSplashFinished then verify SpacecraftList navigation command was sent`() = testScope.runTest {
        // Given
        val expectedNavigationCommand = SpacecraftListPresentationDestination

        // When
        classUnderTest.onSplashFinished()

        // Then
        val actualNavigationCommand = classUnderTest.navigationFlow.first()
        assertEquals(expectedNavigationCommand, actualNavigationCommand)
    }
}
