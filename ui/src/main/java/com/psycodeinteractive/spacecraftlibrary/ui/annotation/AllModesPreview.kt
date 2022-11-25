package com.psycodeinteractive.spacecraftlibrary.ui.annotation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
annotation class AllModesPreview
