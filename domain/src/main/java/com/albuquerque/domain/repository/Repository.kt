package com.albuquerque.domain.repository

import com.albuquerque.data.dto.CategoryDTO
import com.albuquerque.data.dto.RestaurantDTO
import com.albuquerque.data.entity.CategoryEntity
import com.albuquerque.data.entity.FoodEntity
import com.albuquerque.data.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getMenuFromApi(): Result<List<CategoryDTO>>

    fun getMenuFromDb(): Flow<List<MenuEntity>>

    suspend fun selectFood(foodId: Long)

    fun getCartFood(): Flow<List<FoodEntity>>

    suspend fun clearCart()

    suspend fun getRestaurantFromApi(): Result<RestaurantDTO>

}