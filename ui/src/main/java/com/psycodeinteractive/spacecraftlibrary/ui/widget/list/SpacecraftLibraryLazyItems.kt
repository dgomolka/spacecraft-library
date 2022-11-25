package com.psycodeinteractive.spacecraftlibrary.ui.widget.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.psycodeinteractive.spacecraftlibrary.ui.R
import com.psycodeinteractive.spacecraftlibrary.ui.extension.value
import com.psycodeinteractive.spacecraftlibrary.ui.themeTypography
import com.psycodeinteractive.spacecraftlibrary.ui.widget.list.LazyItemsArrangement.Horizontal
import com.psycodeinteractive.spacecraftlibrary.ui.widget.list.LazyItemsArrangement.Vertical
import androidx.paging.compose.itemsIndexed as pagingItemsIndexed

@Composable
fun <Model : Any> SpacecraftLibraryLazyRow(
    modifier: Modifier = Modifier,
    items: List<Model>,
    itemSpacing: Dp = defaultItemSpacing,
    key: ((index: Int, item: Model) -> Any)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    lazyListScope: LazyListScope.() -> Unit = {},
    lazyItemScope: @Composable LazyItemScope.(index: Int, item: Model) -> Unit = { _, _ -> }
) {
    ListContainer(modifier, items, lazyListScope, lazyItemScope, itemSpacing, key, contentPadding, Horizontal)
}

@Composable
fun <Model : Any> SpacecraftLibraryLazyColumn(
    modifier: Modifier = Modifier,
    items: List<Model>,
    itemSpacing: Dp = defaultItemSpacing,
    key: ((index: Int, item: Model) -> Any)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    lazyListScope: LazyListScope.() -> Unit = {},
    lazyItemScope: @Composable LazyItemScope.(index: Int, item: Model) -> Unit = { _, _ -> }
) {
    ListContainer(modifier, items, lazyListScope, lazyItemScope, itemSpacing, key, contentPadding, Vertical)
}

@Composable
fun <Model : Any> SpacecraftLibraryPagedLazyColumn(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Model>,
    itemSpacing: Dp = defaultItemSpacing,
    key: ((index: Int, item: Model) -> Any)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    lazyListScope: LazyListScope.() -> Unit = {},
    lazyItemScope: @Composable LazyItemScope.(index: Int, item: Model) -> Unit = { _, _ -> }
) {
    PagedListContainer(modifier, items, lazyListScope, lazyItemScope, itemSpacing, key, contentPadding, Vertical)
}

@Composable
private fun <Model : Any> ListContainer(
    modifier: Modifier = Modifier,
    items: List<Model>,
    lazyListScope: LazyListScope.() -> Unit = {},
    lazyItemScope: @Composable LazyItemScope.(index: Int, item: Model) -> Unit = { _, _ -> },
    itemSpacing: Dp,
    key: ((index: Int, item: Model) -> Any)? = null,
    contentPadding: PaddingValues,
    arrangement: LazyItemsArrangement
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Crossfade(targetState = items.isEmpty()) { isEmpty ->
            when (isEmpty) {
                true -> EmptyListIndicator()
                false -> List(items, null, lazyListScope, lazyItemScope, itemSpacing, key, contentPadding, arrangement)
            }
        }
    }
}

@Composable
private fun <Model : Any> PagedListContainer(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Model>,
    lazyListScope: LazyListScope.() -> Unit = {},
    lazyItemScope: @Composable LazyItemScope.(index: Int, item: Model) -> Unit = { _, _ -> },
    itemSpacing: Dp,
    key: ((index: Int, item: Model) -> Any)? = null,
    contentPadding: PaddingValues,
    arrangement: LazyItemsArrangement
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Crossfade(targetState = items.itemSnapshotList.isEmpty()) { isEmpty ->
            when (isEmpty) {
                true -> EmptyListIndicator()
                false -> List(null, items, lazyListScope, lazyItemScope, itemSpacing, key, contentPadding, arrangement)
            }
        }
    }
}

@Composable
private fun <Model : Any> List(
    items: List<Model>?,
    pagingItems: LazyPagingItems<Model>?,
    lazyListScope: LazyListScope.() -> Unit = {},
    lazyItemScope: @Composable LazyItemScope.(index: Int, item: Model) -> Unit = { _, _ -> },
    itemSpacing: Dp,
    key: ((index: Int, item: Model) -> Any)? = null,
    contentPadding: PaddingValues,
    arrangement: LazyItemsArrangement
) {
    val content: LazyListScope.() -> Unit = {
        if (items != null) {
            itemsIndexed(
                items = items,
                key = key
            ) { index: Int, item: Model ->
                lazyItemScope(index, item)
            }
        } else if (pagingItems != null) {
            pagingItemsIndexed(
                items = pagingItems,
                key = key
            ) { index: Int, item: Model? ->
                if (item != null) {
                    lazyItemScope(index, item)
                }
            }
        }
        lazyListScope()
    }
    val listState = pagingItems?.rememberLazyListState() ?: rememberLazyListState()

    val spacing = Arrangement.spacedBy(itemSpacing)
    when (arrangement) {
        Vertical -> LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = content,
            state = listState,
            contentPadding = contentPadding,
            verticalArrangement = spacing
        )
        Horizontal -> LazyRow(
            modifier = Modifier.fillMaxSize(),
            content = content,
            state = listState,
            contentPadding = contentPadding,
            horizontalArrangement = spacing
        )
    }
}

@Composable
private fun BoxScope.EmptyListIndicator() {
    Text(
        modifier = Modifier.align(Center),
        text = R.string.no_items.value,
        textAlign = TextAlign.Center,
        style = themeTypography.h5
    )
}

@Composable
fun <T : Any> LazyPagingItems<T>.rememberLazyListState(): LazyListState {
    // After recreation, LazyPagingItems first return 0 items, then the cached items.
    // This behavior/issue is resetting the LazyListState scroll position.
    // Below is a workaround. More info: https://issuetracker.google.com/issues/177245496.
    return when (itemCount) {
        // Return a different LazyListState instance.
        0 -> remember(this) { LazyListState(0, 0) }
        // Return rememberLazyListState (normal case).
        else -> {
            androidx.compose.foundation.lazy.rememberLazyListState()
        }
    }
}

private enum class LazyItemsArrangement {
    Vertical, Horizontal
}

private val defaultItemSpacing = 10.dp
