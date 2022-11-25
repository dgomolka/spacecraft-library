package com.psycodeinteractive.spacecraftlibrary.data.contract.mapper

import com.psycodeinteractive.spacecraftlibrary.data.contract.model.ApiMapperException
import com.psycodeinteractive.spacecraftlibrary.data.contract.model.DatabaseMapperException
import com.psycodeinteractive.spacecraftlibrary.domain.contract.model.DataMapperException

abstract class ApiToDataMapper<Input : Any, Output : Any> {
    fun toData(input: Input): Output = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DataMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: Input): Output
}

abstract class DataToApiMapper<Input : Any, Output : Any> {
    fun toApi(input: Input): Output = try {
        map(input)
    } catch (throwable: Throwable) {
        throw ApiMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: Input): Output
}

abstract class DatabaseToDataMapper<Input : Any, Output : Any> {
    fun toData(input: Input): Output = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DataMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: Input): Output
}

abstract class DataToDatabaseMapper<Input : Any, Output : Any> {
    fun toDatabase(input: Input): Output = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DatabaseMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: Input): Output
}
