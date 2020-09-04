package com.albuquerque.menuly.app.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao: BaseDao<CategoryEntity> {

    @Transaction
    @Query("SELECT * FROM category")
    fun getDogsAndOwners(): Flow<List<MenuEntity>>

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<CategoryEntity>>

}