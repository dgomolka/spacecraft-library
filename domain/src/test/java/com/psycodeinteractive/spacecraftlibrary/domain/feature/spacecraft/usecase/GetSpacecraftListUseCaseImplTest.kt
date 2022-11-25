package com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase

import androidx.paging.PagingData
import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.TestCoroutineScope
import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.testCoroutineContextProvider
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.repository.SpacecraftRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetSpacecraftListUseCaseImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var spacecraftRepository: SpacecraftRepository

    private lateinit var testScope: TestScope

    private lateinit var classUnderTest: GetSpacecraftListUseCaseImpl

    @Before
    fun setup() {
        testScope = TestCoroutineScope.createTestScope()
        classUnderTest = GetSpacecraftListUseCaseImpl(spacecraftRepository, testCoroutineContextProvider)
    }

    @Test
    fun `When executeInBackground Then verify getSpacecraftList returned paging data`() = testScope.runTest {
        // Given
        val expectedValue = flowOf(PagingData.from(emptyList<SpacecraftDomainModel>()))
        coEvery { spacecraftRepository.getSpacecraftList() } returns expectedValue

        // When
        val actualValue = classUnderTest.executeInBackground(Unit, testScope)

        // Then
        coVerify(exactly = 1) { spacecraftRepository.getSpacecraftList() }
        assertEquals(expectedValue, actualValue)
    }
}
