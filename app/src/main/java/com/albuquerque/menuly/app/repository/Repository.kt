package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.data.dto.CategoryDTO
import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getMenuFromApi(): Result<List<CategoryDTO>>

    fun getMenuFromDb(): Flow<List<MenuEntity>>

    fun getCategoriesFromDb(): Flow<List<CategoryEntity>>

}