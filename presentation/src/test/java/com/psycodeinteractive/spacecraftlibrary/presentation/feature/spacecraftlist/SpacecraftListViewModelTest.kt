package com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftlist

import android.util.Log
import androidx.paging.PagingData
import com.psycodeinteractive.spacecraftlibrary.domain.execution.UseCaseExecutor
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.AgencyDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftConfigDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.SpacecraftDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.StatusDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.model.TypeDomainModel
import com.psycodeinteractive.spacecraftlibrary.domain.feature.spacecraft.usecase.GetSpacecraftListUseCase
import com.psycodeinteractive.spacecraftlibrary.domain.logger.Logger
import com.psycodeinteractive.spacecraftlibrary.presentation.coroutine.TestCoroutineScope
import com.psycodeinteractive.spacecraftlibrary.presentation.extension.givenSuccessfulNoArgumentUseCaseExecution
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.SpacecraftDetailsPresentationDestination
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.mapper.SpacecraftDomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.AgencyPresentationModel
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.SpacecraftConfigPresentationModel
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.SpacecraftPresentationModel
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.StatusPresentationModel
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.model.TypePresentationModel
import com.psycodeinteractive.spacecraftlibrary.presentation.mapper.DefaultDomainToPresentationExceptionMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.paging.PagingTestUtil
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private val expectedDomainSpacecraft = SpacecraftDomainModel(
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
        agency = AgencyDomainModel.AgencyInfoDomainModel(
            1,
            "www.imageagency.com",
            "Agency1",
            "agencytype1"
        ),
        isInUse = true,
        imageUrl = "www.imageconfig.com"
    )
)

private val expectedPresentationSpacecraft = SpacecraftPresentationModel(
    id = 1,
    url = "www.spacecraft1.com",
    name = "Spacecraft1",
    serialNumber = "123",
    status = StatusPresentationModel(1, "status1"),
    description = "Description1",
    spacecraftConfig = SpacecraftConfigPresentationModel(
        id = 1,
        url = "www.config.com",
        name = "spacecraft config 1",
        type = TypePresentationModel(1, "type1"),
        agency = AgencyPresentationModel.AgencyInfoPresentationModel(
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
class SpacecraftListViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var useCaseExecutor: UseCaseExecutor

    @MockK
    lateinit var defaultDomainToPresentationExceptionMapper: DefaultDomainToPresentationExceptionMapper

    @MockK
    lateinit var spacecraftDomainToPresentationMapper: SpacecraftDomainToPresentationMapper

    @MockK
    lateinit var getSpacecraftListUseCase: GetSpacecraftListUseCase

    @MockK
    lateinit var logger: Logger

    private lateinit var testScope: TestScope

    private lateinit var classUnderTest: SpacecraftListViewModel

    @Before
    fun setup() {
        testScope = TestCoroutineScope.createTestScope()
        classUnderTest = SpacecraftListViewModel(
            spacecraftDomainToPresentationMapper = spacecraftDomainToPresentationMapper,
            getSpacecraftListUseCase = getSpacecraftListUseCase,
            useCaseExecutorProvider = { useCaseExecutor },
            domainToPresentationExceptionMapper = defaultDomainToPresentationExceptionMapper,
            logger = logger
        )
    }

    @Test
    fun `When onViewCreated Then getSpacecraftListUseCase was executed and ViewState updated`() = testScope.runTest {
        // Given
        every {
            spacecraftDomainToPresentationMapper.toPresentation(expectedDomainSpacecraft)
        } returns expectedPresentationSpacecraft

        val expectedPresentationPagingItems = listOf(expectedPresentationSpacecraft)
        val expectedDomainFlow = flowOf(PagingData.from(listOf(expectedDomainSpacecraft)))

        useCaseExecutor.givenSuccessfulNoArgumentUseCaseExecution(
            getSpacecraftListUseCase,
            expectedDomainFlow
        )

        // Mock Log class for AsyncPagingDataDiffer which uses it
        mockkStatic(Log::class)
        every { Log.isLoggable(any(), any()) } returns false
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0

        // When
        classUnderTest.onViewCreated()
        advanceUntilIdle()

        // Then
        val differ = PagingTestUtil.createPagingDiffer<SpacecraftPresentationModel>()
        differ.submitData(classUnderTest.currentViewState().spacecraftList.first())
        val actualPagingItems = differ.snapshot().items

        assertEquals(expectedPresentationPagingItems, actualPagingItems)
    }

    @Test
    fun `When onViewCreated called multiple times Then getSpacecraftListUseCase was executed only once`() = testScope.runTest {
        useCaseExecutor.givenSuccessfulNoArgumentUseCaseExecution(
            getSpacecraftListUseCase,
            emptyFlow()
        )

        // When
        repeat(5) {
            classUnderTest.onViewCreated()
        }
        advanceUntilIdle()

        // Then
        verify(exactly = 1) {
            useCaseExecutor.execute(
                getSpacecraftListUseCase,
                callback = any(),
                onError = any()
            )
        }
    }

    @Test
    fun `Given spacecraftId When onSpacecraftClick Then navigate to SpacecraftDetails`() = testScope.runTest {
        // Given
        val givenId = 123
        val expectedPresentationDestination = SpacecraftDetailsPresentationDestination(givenId)

        // When
        classUnderTest.onSpacecraftClick(givenId)

        // Then
        val actualPresentationDestination = classUnderTest.navigationFlow.first()
        assertEquals(expectedPresentationDestination, actualPresentationDestination)
    }
}
