package com.psycodeinteractive.spacecraftlibrary.ui.extension

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

val @receiver:StringRes Int.value: String
    @Composable get() = stringResource(id = this)
