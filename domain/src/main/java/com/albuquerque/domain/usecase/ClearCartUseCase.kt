package com.albuquerque.domain.usecase

import com.albuquerque.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClearCartUseCase(
    private val repository: Repository
) {

    suspend fun invoke() {
        withContext(Dispatchers.IO) {
            repository.clearCart()
        }
    }

}