package com.psycodeinteractive.spacecraftlibrary.presentation.model.exception

abstract class PresentationException(open val throwable: Throwable) : Exception(throwable) {
    constructor(message: String) : this(Exception(message))
}
