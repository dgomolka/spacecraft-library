package com.psycodeinteractive.spacecraftlibrary.presentation.mapper

import com.psycodeinteractive.spacecraftlibrary.domain.model.exception.DomainException
import com.psycodeinteractive.spacecraftlibrary.domain.model.exception.UnknownDomainException
import com.psycodeinteractive.spacecraftlibrary.presentation.contract.mapper.DomainToPresentationMapper
import com.psycodeinteractive.spacecraftlibrary.presentation.model.exception.PresentationException
import com.psycodeinteractive.spacecraftlibrary.presentation.model.exception.UnknownPresentationException

class DefaultDomainToPresentationExceptionMapper : DomainToPresentationMapper<DomainException, PresentationException>() {
    override fun map(input: DomainException) = when (input) {
        is UnknownDomainException -> UnknownPresentationException(input)
        else -> UnknownPresentationException(input)
    }
}
