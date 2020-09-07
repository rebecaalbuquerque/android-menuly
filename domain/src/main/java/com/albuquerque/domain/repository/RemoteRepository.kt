package com.albuquerque.domain.repository

import com.albuquerque.data.dto.CategoryDTO
import com.albuquerque.data.dto.RestaurantDTO

interface RemoteRepository {

    suspend fun fetchMenu(): Result<List<CategoryDTO>>

    suspend fun fetchRestaurant(): Result<RestaurantDTO>

}