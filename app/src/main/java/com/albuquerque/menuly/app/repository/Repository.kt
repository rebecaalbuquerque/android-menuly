package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.data.dto.CategoryDTO
import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getMenuFromApi(): Result<List<CategoryDTO>>

    suspend fun getMenuFromDb(): Flow<List<MenuEntity>>

    suspend fun getCategoriesFromDb(): Flow<List<CategoryEntity>>

}