package com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model

import androidx.annotation.StringRes

sealed class TopBarActionUiModel(
    @StringRes val textResource: Int = 0
) {
    object None : TopBarActionUiModel()
}
