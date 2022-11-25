package com.psycodeinteractive.spacecraftlibrary.presentation.model.exception

data class UnknownPresentationException(override val throwable: Throwable) : PresentationException(throwable) {
    constructor(errorMessage: String) : this(Throwable(errorMessage))
}
