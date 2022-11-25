package com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.psycodeinteractive.spacecraftlibrary.ui.extension.value
import com.psycodeinteractive.spacecraftlibrary.ui.semiBold
import com.psycodeinteractive.spacecraftlibrary.ui.themeTypography
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model.BackNavigationTypeUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model.TopBarActionUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model.TopBarResourcesUiModel

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    resources: TopBarResourcesUiModel?,
    showAction: Boolean = true,
    onActionClick: ((action: TopBarActionUiModel) -> Unit)? = null,
    onBackNavigationClick: ((BackNavigationTypeUiModel) -> Unit)? = null
) {
    resources?.run {
        if (titleBigTextResource == null && titleSmallTextResource == null) {
            return
        }

        val titleBigText = titleBigTextResource?.run { value }
        val titleSmallText = titleSmallTextResource?.run { value }

        val isTitleBigProvided = titleBigText != null

        val titleBigPadding = if (isTitleBigProvided) 24.dp else 0.dp

        val paddingModifier = Modifier.absolutePadding(
            top = titleBigPadding,
            bottom = titleBigPadding,
            left = 0.dp,
            right = 0.dp
        )

        TopAppBar(
            modifier = modifier
                then Modifier.background(MaterialTheme.colors.surface)
                then paddingModifier,
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                NavigationButton(
                    modifier = Modifier.align(CenterStart),
                    type = backNavigationType,
                    onClick = onBackNavigationClick
                )
                Title(isTitleBigProvided, titleBigText, titleSmallText)
                Action(topBarAction, showAction, onActionClick)
            }
        }
    }
}

@Composable
private fun BoxScope.Title(
    isTitleBigProvided: Boolean,
    titleBig: String?,
    titleSmall: String?
) {
    val titleTextAlignment = if (isTitleBigProvided) CenterStart else Center
    val titleTextStyle = if (isTitleBigProvided) themeTypography.h5 else themeTypography.subtitle1.semiBold()
    val titleText = (titleBig ?: titleSmall).orEmpty()
    Text(
        modifier = Modifier
            .padding(12.dp)
            .align(titleTextAlignment),
        text = titleText,
        color = MaterialTheme.colors.onSurface,
        style = titleTextStyle
    )
}

@Composable
private fun BoxScope.Action(
    topBarAction: TopBarActionUiModel,
    showAction: Boolean,
    onActionClick: ((TopBarActionUiModel) -> Unit)?
) {
    if (topBarAction == TopBarActionUiModel.None) return

    val actionText = topBarAction.textResource.value
    AnimatedVisibility(
        modifier = Modifier
            .wrapContentSize()
            .align(CenterEnd),
        visible = showAction
    ) {
        Text(
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable {
                    onActionClick?.invoke(topBarAction)
                },
            text = actionText,
            maxLines = 1,
            color = MaterialTheme.colors.primary,
            style = themeTypography.subtitle1
        )
    }
}
