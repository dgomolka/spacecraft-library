package com.psycodeinteractive.spacecraftlibrary.ui.mapper.exception

import com.psycodeinteractive.spacecraftlibrary.presentation.model.exception.CustomPresentationException
import com.psycodeinteractive.spacecraftlibrary.presentation.model.exception.PresentationException
import com.psycodeinteractive.spacecraftlibrary.presentation.model.exception.UnknownPresentationException
import com.psycodeinteractive.spacecraftlibrary.ui.contract.mapper.PresentationToUiMapper
import com.psycodeinteractive.spacecraftlibrary.ui.model.exception.CustomUiException
import com.psycodeinteractive.spacecraftlibrary.ui.model.exception.UiException
import com.psycodeinteractive.spacecraftlibrary.ui.model.exception.UnknownUiException

class DefaultPresentationToUiExceptionMapper : PresentationToUiMapper<PresentationException, UiException>() {
    override fun map(input: PresentationException) = when (input) {
        is UnknownPresentationException -> UnknownUiException(input)
        is CustomPresentationException -> CustomUiException(input.message, input)
        else -> UnknownUiException(input)
    }
}
