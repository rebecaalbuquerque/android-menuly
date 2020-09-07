package com.albuquerque.domain.usecase

import com.albuquerque.data.toUI
import com.albuquerque.data.ui.RestaurantUI
import com.albuquerque.domain.repository.Repository

class GetRestaurantUseCase(
    private val repository: Repository
) {

    suspend fun invoke(): Result<RestaurantUI> {
        return repository.getRestaurantFromApi().map { it.toUI() }
    }

}