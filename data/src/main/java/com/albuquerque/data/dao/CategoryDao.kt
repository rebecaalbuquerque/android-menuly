package com.albuquerque.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.albuquerque.data.entity.CategoryEntity
import com.albuquerque.data.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao: BaseDao<CategoryEntity> {

    @Transaction
    @Query("SELECT * FROM category")
    fun getMenuFlow(): Flow<List<MenuEntity>>

}