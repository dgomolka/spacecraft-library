package com.psycodeinteractive.spacecraftlibrary.presentation.model.exception

data class CustomPresentationException(override val message: String) : PresentationException(message)
