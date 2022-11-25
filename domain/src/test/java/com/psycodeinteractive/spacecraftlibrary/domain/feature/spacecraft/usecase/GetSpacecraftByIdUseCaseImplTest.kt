package com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase

import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.TestCoroutineScope
import com.psycodeinteractive.spacecraftlibrary.domain.coroutine.testCoroutineContextProvider
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.AgencyDomainModel.AgencyInfoDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftConfigDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.StatusDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.TypeDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.repository.SpacecraftRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private val expectedSpacecraft = SpacecraftDomainModel(
    id = 1,
    url = "www.spacecraft1.com",
    name = "Spacecraft1",
    serialNumber = "123",
    status = StatusDomainModel(1, "status1"),
    description = "Description1",
    spacecraftConfig = SpacecraftConfigDomainModel(
        id = 1,
        url = "www.config.com",
        name = "spacecraft config 1",
        type = TypeDomainModel(1, "type1"),
        agency = AgencyInfoDomainModel(
            1,
            "www.imageagency.com",
            "Agency1",
            "agencytype1"
        ),
        isInUse = true,
        imageUrl = "www.imageconfig.com"
    )
)

@OptIn(ExperimentalCoroutinesApi::class)
class GetSpacecraftByIdUseCaseImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var spacecraftRepository: SpacecraftRepository

    private lateinit var testScope: TestScope

    private lateinit var classUnderTest: GetSpacecraftByIdUseCaseImpl

    @Before
    fun setup() {
        testScope = TestCoroutineScope.createTestScope()
        classUnderTest = GetSpacecraftByIdUseCaseImpl(spacecraftRepository, testCoroutineContextProvider)
    }

    @Test
    fun `When executeInBackground Then verify getSpacecraftById returned spacecraft`() = testScope.runTest {
        // Given
        val givenId = 1234
        coEvery { spacecraftRepository.getSpacecraft(any()) } returns expectedSpacecraft

        // When
        val actualValue = classUnderTest.executeInBackground(givenId, testScope)

        // Then
        coVerify(exactly = 1) { spacecraftRepository.getSpacecraft(givenId) }
        assertEquals(expectedSpacecraft, actualValue)
    }
}
