package com.albuquerque.domain.usecase

import com.albuquerque.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CheckOutUseCase(
    private val repository: Repository
) {

    suspend fun invoke(): Result<Any> {

        withContext(Dispatchers.IO) {
            delay(500)
        }

        return Result.success(Any())
    }

}