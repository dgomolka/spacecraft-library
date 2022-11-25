package com.psycodeinteractive.spacecraftlibrary.ui.feature.spacecraftdetails.model

import com.psycodeinteractive.spacecraftlibrary.ui.R
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model.BackNavigationTypeUiModel
import com.psycodeinteractive.spacecraftlibrary.ui.widget.topbar.model.TopBarResourcesUiModel

object SpacecraftDetailsTopBarResourcesUiModel : TopBarResourcesUiModel(
    titleSmallTextResource = R.string.spacecraft_details,
    backNavigationType = BackNavigationTypeUiModel.Close
)
