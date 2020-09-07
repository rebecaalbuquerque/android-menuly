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

    fun getCategoriesWithFood(): Flow<List<MenuEntity>>

    fun getCategories(): Flow<List<CategoryEntity>>

}