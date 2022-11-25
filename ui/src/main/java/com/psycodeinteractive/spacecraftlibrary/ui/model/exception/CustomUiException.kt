package com.psycodeinteractive.spacecraftlibrary.ui.model.exception

import android.content.res.Resources
import com.psycodeinteractive.spacecraftlibrary.ui.R

class CustomUiException(
    override val message: String,
    throwable: Throwable
) : UiException(
    throwable = throwable,
    titleResourceId = R.string.unknown_exception_title
) {
    override fun getMessageText(resources: Resources) = message
}
