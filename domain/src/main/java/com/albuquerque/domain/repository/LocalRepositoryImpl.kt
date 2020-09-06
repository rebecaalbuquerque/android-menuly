package com.albuquerque.domain.repository

import com.albuquerque.data.dao.CategoryDao
import com.albuquerque.data.dao.FoodDao
import com.albuquerque.data.entity.CategoryEntity
import com.albuquerque.data.entity.FoodEntity
import com.albuquerque.data.entity.MenuEntity
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