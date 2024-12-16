package com.amirnlz.core.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in Params, Result> {
    protected abstract suspend fun execute(params: Params): Result


    suspend operator fun invoke(
        params: Params,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Result {
        return withContext(dispatcher) {
            execute(params)
        }
    }
}