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

    override suspend fun saveFood(food: List<FoodEntity>) {
        foodDao.saveAll(food)
    }

    override suspend fun updateFood(food: FoodEntity) {
        foodDao.update(food)
    }

    override suspend fun getFoodById(foodId: Long): FoodEntity? = foodDao.get(foodId)

    override suspend fun saveCategories(categories: List<CategoryEntity>) {
        categoryDao.insertAll(categories)
    }

    override fun getMenuFlow(): Flow<List<MenuEntity>> =
        categoryDao.getMenuFlow()

    override fun getCartFoodFlow(): Flow<List<FoodEntity>> =
        foodDao.getCartFoodFlow()

    override suspend fun clearCart() = foodDao.clearCart()

}