package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.entity.MenuEntity
import com.albuquerque.menuly.app.data.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun saveCategories(categories: List<CategoryEntity>)

    suspend fun saveFood(food: List<FoodEntity>)

    fun getCategoriesWithFood(): Flow<List<MenuEntity>>

    fun getCategories(): Flow<List<CategoryEntity>>

}