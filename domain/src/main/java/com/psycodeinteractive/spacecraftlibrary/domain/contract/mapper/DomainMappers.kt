package com.psycodeinteractive.spacecraftlibrary.domain.contract.mapper

import com.psycodeinteractive.spacecraftlibrary.domain.contract.model.DataMapperException
import com.psycodeinteractive.spacecraftlibrary.domain.contract.model.DomainMapperException

abstract class DataToDomainMapper<Input : Any, Output : Any> {
    fun toDomain(input: Input): Output = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: Input): Output
}

abstract class DomainToDataMapper<Input : Any, Output : Any> {
    fun toData(input: Input): Output = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DataMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: Input): Output
}
