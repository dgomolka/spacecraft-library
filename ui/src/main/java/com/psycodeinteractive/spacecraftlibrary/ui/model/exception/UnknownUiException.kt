package com.psycodeinteractive.spacecraftlibrary.ui.model.exception

import com.psycodeinteractive.spacecraftlibrary.ui.R

class UnknownUiException(
    throwable: Throwable
) : UiException(
    throwable,
    R.string.unknown_exception_title,
    R.string.unknown_exception_description
)
