package com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase

import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.TestCoroutineScope
import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.testCoroutineContextProvider
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.repository.AppSessionRepository
import com.psycodeinteractive.spacecraftlibrary.domain.feature.session.usecase.StopSessionUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StopSessionUseCaseImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var appSessionRepository: AppSessionRepository

    private lateinit var testScope: TestScope

    private lateinit var classUnderTest: StopSessionUseCaseImpl

    @Before
    fun setup() {
        testScope = TestCoroutineScope.createTestScope()
        classUnderTest = StopSessionUseCaseImpl(appSessionRepository, testCoroutineContextProvider)
    }

    @Test
    fun `When executeInBackground Then verify stopSession was called`() = testScope.runTest {
        // Given
        coEvery { appSessionRepository.stopSession() } just runs

        // When
        classUnderTest.executeInBackground(Unit, testScope)

        // Then
        coVerify(exactly = 1) { appSessionRepository.stopSession() }
    }
}
