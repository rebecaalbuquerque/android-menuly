package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.data.dao.CategoryDao
import com.albuquerque.menuly.app.data.dao.FoodDao
import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.entity.MenuEntity
import com.albuquerque.menuly.app.data.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(
    private val categoryDao: CategoryDao,
    private val foodDao: FoodDao
): LocalRepository {

    override suspend fun saveCategories(categories: List<CategoryEntity>) {
        categoryDao.insertAll(categories)
    }

    override suspend fun saveFood(food: List<FoodEntity>) {
        foodDao.insertAll(food)
    }

    override fun getCategoriesWithFood(): Flow<List<MenuEntity>> =
        categoryDao.getDogsAndOwners()

    override fun getCategories(): Flow<List<CategoryEntity>> =
        categoryDao.getCategories()

}