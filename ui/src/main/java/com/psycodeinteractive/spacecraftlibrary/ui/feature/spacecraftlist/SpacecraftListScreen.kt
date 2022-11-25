package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftdetails.SpacecraftDetailsPresentationDestination
import com.psycodeinteractive.spacecraftlibrary.presentation.feature.spacecraftlist.SpacecraftListViewModel
import com.psycodeinteractive.spacecraftlibrary.ui.R
import com.psycodeinteractive.spacecraftlibrary.ui.SpacecraftLibraryTheme
import com.psycodeinteractive.spacecraftlibrary.ui.annotation.AllModesPreview
import com.psycodeinteractive.spacecraftlibrary.ui.extension.value
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.mapper.SpacecraftPresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.SpacecraftUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model.SpacecraftUiModelPreviewProvider
import com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftlist.model.SpacecraftListTopBarResourcesUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.mapper.exception.DefaultPresentationToUiExceptionMapper
import com.psycodeinteractive.spacecraftlibrary.ui.screen.Screen
import com.psycodeinteractive.spacecraftlibrary.ui.screen.observeWithLifecycle
import com.psycodeinteractive.spacecraftlibrary.ui.themeTypography
import com.psycodeinteractive.spacecraftlibrary.ui.widget.LabeledChip
import com.psycodeinteractive.spacecraftlibrary.ui.widget.LoadingIndicator
import com.psycodeinteractive.spacecraftlibrary.ui.widget.image.SpacecraftLibraryImage
import com.psycodeinteractive.spacecraftlibrary.ui.widget.list.SpacecraftLibraryPagedLazyColumn
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@Destination
@Composable
fun SpacecraftListScreen(
    spacecraftPresentationToUiMapper: SpacecraftPresentationToUiMapper,
    presentationToUiExceptionMapper: DefaultPresentationToUiExceptionMapper,
    goToSpacecraftDetails: (id: Int) -> Unit
) {
    Screen<SpacecraftListViewModel, _, _>(
        presentationToUiExceptionMapper = presentationToUiExceptionMapper
    ) { viewModel, viewState, _ ->
        val spacecraftList = remember(viewState.spacecraftList) {
            viewState.spacecraftList.map { pagingData ->
                pagingData.map(spacecraftPresentationToUiMapper::toUi)
            }
        }.collectAsLazyPagingItems()

        SpacecraftListContent(
            spacecraftList = spacecraftList,
            viewModel = viewModel,
            onSpacecraftClick = viewModel::onSpacecraftClick
        )

        HandleNavigation(viewModel, goToSpacecraftDetails)
    }
}

@Composable
private fun HandleNavigation(
    viewModel: SpacecraftListViewModel,
    goToSpacecraftDetails: (id: Int) -> Unit
) {
    viewModel.navigationFlow.observeWithLifecycle { destination ->
        when (destination) {
            is SpacecraftDetailsPresentationDestination -> goToSpacecraftDetails(destination.spacecraftId)
        }
    }
}

@Composable
private fun SpacecraftListContent(
    spacecraftList: LazyPagingItems<SpacecraftUiModel>,
    viewModel: SpacecraftListViewModel,
    onSpacecraftClick: (id: Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            resources = SpacecraftListTopBarResourcesUiModel
        )
        Divider(
            color = topDividerColor
        )
        SpacecraftLibraryPagedLazyColumn(
            items = spacecraftList,
            key = { _, item ->
                item.id
            },
            lazyListScope = {
                when (val appendLoadState = spacecraftList.loadState.append) {
                    is NotLoading -> Unit
                    Loading -> item { LoadingIndicator() }
                    is Error -> viewModel.onError(appendLoadState.error.message.toString())
                }
            }
        ) { index, item ->
            SpacecraftListItem(
                spacecraft = item,
                index = index,
                onClick = onSpacecraftClick
            )
        }
    }
}

@Composable
private fun SpacecraftListItem(
    spacecraft: SpacecraftUiModel,
    index: Int,
    onClick: (id: Int) -> Unit
) {
    val ripple = rememberRipple(
        color = MaterialTheme.colors.primary
    )
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = ripple,
            onClick = { onClick(spacecraft.id) }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = listItemPadding, end = listItemPadding, top = listItemPadding)
        ) {
            Row(
                modifier = Modifier.align(Start)
            ) {
                Column {
                    Text(
                        text = "${R.string.number.value} ${index + 1}",
                        style = themeTypography.caption
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.7f),
                        maxLines = 2,
                        overflow = Ellipsis,
                        text = spacecraft.name,
                        style = themeTypography.body1
                    )
                    LabeledChip(
                        labelText = R.string.in_use.value,
                        smaller = true,
                        valueText = if (spacecraft.spacecraftConfig.isInUse) {
                            R.string.yes.value
                        } else {
                            R.string.no.value
                        },
                        chipColorDark = if (spacecraft.spacecraftConfig.isInUse) {
                            yesGreenColor
                        } else {
                            noRedColor
                        }
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                SpacecraftLibraryImage(
                    modifier = Modifier.size(imageSize),
                    url = spacecraft.spacecraftConfig.imageUrl
                )
            }

            Divider(
                modifier = Modifier.padding(top = listItemPadding),
                color = listItemDividerColor
            )
        }
    }
}

@Composable
@AllModesPreview
private fun MostWantedListScreenContentPreview(
    @PreviewParameter(SpacecraftUiModelPreviewProvider::class) spacecraftList: List<SpacecraftUiModel>
) {
    SpacecraftLibraryTheme {
        SpacecraftListContent(
            spacecraftList = flowOf(PagingData.from(spacecraftList)).collectAsLazyPagingItems(),
            viewModel = hiltViewModel()
        ) {}
    }
}

private val listItemPadding = 16.dp

private val imageSize = 50.dp

private val listItemDividerColor = Color.LightGray.copy(0.3f)
private val topDividerColor = Color.LightGray.copy(0.35f)
private val yesGreenColor = Color.Green.copy(0.4f)
private val noRedColor = Color.Red.copy(0.4f)
