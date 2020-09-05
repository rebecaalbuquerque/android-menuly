package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.data.dto.CategoryDTO
import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.entity.MenuEntity
import com.albuquerque.menuly.app.data.entity.FoodEntity
import com.albuquerque.menuly.app.data.toEntity
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    val remote: RemoteRepository,
    val local: LocalRepository
): Repository {

    override suspend fun getMenuFromApi(): Result<List<CategoryDTO>> {
        return remote
            .fetchMenu()
            .onSuccess { response ->
                val categories = mutableListOf<CategoryEntity>()
                val food = mutableListOf<FoodEntity>()

                response.forEach { category ->
                    categories.add(category.toEntity())
                    food.addAll(category.items.map { it.toEntity(it.id) })
                }

                local.saveCategories(categories)
                local.saveFood(food)
            }
    }

    override fun getMenuFromDb(): Flow<List<MenuEntity>> =
        local.getCategoriesWithFood()

    override fun getCategoriesFromDb(): Flow<List<CategoryEntity>> =
        local.getCategories()
}