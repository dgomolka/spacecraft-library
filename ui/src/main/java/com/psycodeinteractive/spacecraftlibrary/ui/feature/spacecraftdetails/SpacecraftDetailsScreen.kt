package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle.State.CREATED
import com.psycodeinteractive.spacecraftlibrary.presentation.destination.BackDestination
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.SpacecraftDetailsViewModel
import com.psycodeinteractive.spacecraftlibrary.ui.R
import com.psycodeinteractive.spacecraftlibrary.ui.SpacecraftLibraryTheme
import com.psycodeinteractive.spacecraftlibrary.ui.annotation.AllModesPreview
import com.psycodeinteractive.spacecraftlibrary.ui.bold
import com.psycodeinteractive.spacecraftlibrary.ui.extension.value
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper.SpacecraftPresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.AgencyUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.AgencyUiModel.AgencyInfoUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.SpacecraftDetailsTopBarResourcesUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.SpacecraftUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.SpacecraftUiModelPreviewProvider
import com.psycodeinteractive.spacecraftlibrary.ui.mapper.exception.DefaultPresentationToUiExceptionMapper
import com.psycodeinteractive.spacecraftlibrary.ui.screen.OnLifecycle
import com.psycodeinteractive.spacecraftlibrary.ui.screen.Screen
import com.psycodeinteractive.spacecraftlibrary.ui.screen.observeWithLifecycle
import com.psycodeinteractive.spacecraftlibrary.ui.widget.LabeledChip
import com.psycodeinteractive.spacecraftlibrary.ui.widget.image.SpacecraftLibraryImage
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.TopBar
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model.BackNavigationTypeUiModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SpacecraftDetailsScreen(
    presentationToUiExceptionMapper: DefaultPresentationToUiExceptionMapper,
    spacecraftPresentationToUiMapper: SpacecraftPresentationToUiMapper,
    spacecraftId: Int,
    onCloseClick: () -> Unit
) {
    Screen<SpacecraftDetailsViewModel, _, _>(
        presentationToUiExceptionMapper = presentationToUiExceptionMapper
    ) { viewModel, viewState, _ ->
        OnLifecycle(minActiveState = CREATED) {
            viewModel.onViewCreated(spacecraftId)
        }

        val spacecraft = viewState.spacecraft?.let(spacecraftPresentationToUiMapper::toUi)

        SpacecraftDetailsScreenContent(spacecraft) {
            viewModel.onCloseAction()
        }

        HandleNavigation(viewModel, onCloseClick)
    }
}

@Composable
fun HandleNavigation(
    viewModel: SpacecraftDetailsViewModel,
    onCloseClick: () -> Unit
) {
    viewModel.navigationFlow.observeWithLifecycle { destination ->
        when (destination) {
            BackDestination -> onCloseClick()
        }
    }
}

@Composable
private fun SpacecraftDetailsScreenContent(
    spacecraft: SpacecraftUiModel?,
    onBackNavigationClick: (BackNavigationTypeUiModel) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            resources = SpacecraftDetailsTopBarResourcesUiModel,
            onBackNavigationClick = onBackNavigationClick
        )
        Divider(
            color = topDividerColor
        )
        SpacecraftDetails(spacecraft)
    }
}

@Composable
private fun SpacecraftDetails(spacecraft: SpacecraftUiModel?) {
    spacecraft?.run {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(columnItemsSpacing)
        ) {
            Title(name)
            SpacecraftLibraryImage(
                modifier = Modifier.aspectRatio(16 / 9f),
                url = spacecraft.spacecraftConfig.imageUrl
            )
            Agency(spacecraft.spacecraftConfig.agency)
            Label(description)
        }
    }
}

@Composable
private fun Title(title: String) {
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = title,
        style = MaterialTheme.typography.h6.bold()
    )
}

@Composable
private fun Label(title: String) {
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = title,
        style = MaterialTheme.typography.subtitle2
    )
}

@Composable
private fun Agency(agencyUiModel: AgencyUiModel) {
    when (agencyUiModel) {
        is AgencyInfoUiModel -> LabeledChip(labelText = R.string.agency.value, valueText = agencyUiModel.name)
        AgencyUiModel.Unspecified -> return
    }
}

@Composable
@AllModesPreview
private fun MostWantedPersonScreenContentPreview(
    @PreviewParameter(SpacecraftUiModelPreviewProvider::class) spacecraft: SpacecraftUiModel
) {
    SpacecraftLibraryTheme {
        SpacecraftDetailsScreenContent(
            spacecraft = spacecraft,
            onBackNavigationClick = {}
        )
    }
}

private val columnItemsSpacing = 10.dp
private val topDividerColor = Color.LightGray.copy(0.35f)
