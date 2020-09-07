package com.albuquerque.domain.repository

import com.albuquerque.data.dto.CategoryDTO
import com.albuquerque.data.entity.CategoryEntity
import com.albuquerque.data.entity.FoodEntity
import com.albuquerque.data.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getMenuFromApi(): Result<List<CategoryDTO>>

    fun getMenuFromDb(): Flow<List<MenuEntity>>

    fun getCategoriesFromDb(): Flow<List<CategoryEntity>>

    suspend fun selectFood(foodId: Long)

    fun getCartFood(): Flow<List<FoodEntity>>

}