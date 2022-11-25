package com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model

import androidx.annotation.StringRes
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model.BackNavigationTypeUiModel.None

abstract class TopBarResourcesUiModel(
    @StringRes val titleBigTextResource: Int? = null,
    @StringRes val titleSmallTextResource: Int? = null,
    val topBarAction: TopBarActionUiModel = TopBarActionUiModel.None,
    val backNavigationType: BackNavigationTypeUiModel = None
)
