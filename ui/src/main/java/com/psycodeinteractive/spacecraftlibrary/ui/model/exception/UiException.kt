package com.psycodeinteractive.spacecraftlibrary.ui.model.exception

import android.content.res.Resources
import android.view.View.NO_ID
import androidx.annotation.StringRes

abstract class UiException(
    val throwable: Throwable = Throwable(),
    @StringRes private val titleResourceId: Int = NO_ID,
    @StringRes private val messageResourceId: Int = NO_ID
) : Exception(throwable) {
    open fun getTitleText(resources: Resources) = resources.getString(titleResourceId)
    open fun getMessageText(resources: Resources) = resources.getString(messageResourceId)
}
