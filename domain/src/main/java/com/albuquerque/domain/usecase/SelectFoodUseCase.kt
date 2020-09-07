package com.albuquerque.domain.usecase

import com.albuquerque.domain.repository.Repository

class SelectFoodUseCase(
    private val repository: Repository
) {

    suspend fun invoke(foodId: Long) {
        repository.selectFood(foodId)
    }

}