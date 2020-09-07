package com.albuquerque.domain.repository

import com.albuquerque.data.entity.CategoryEntity
import com.albuquerque.data.entity.FoodEntity
import com.albuquerque.data.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun saveFood(food: List<FoodEntity>)

    suspend fun getFoodById(foodId: Long): FoodEntity?

    suspend fun updateFood(food: FoodEntity)

    suspend fun saveCategories(categories: List<CategoryEntity>)

    suspend fun clearCart()

    fun getCartFoodFlow(): Flow<List<FoodEntity>>

    fun getMenuFlow(): Flow<List<MenuEntity>>

}