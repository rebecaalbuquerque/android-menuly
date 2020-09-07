package com.albuquerque.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.albuquerque.data.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao: BaseDao<FoodEntity> {

    @Query("SELECT * FROM food WHERE foodId = :id")
    suspend fun get(id: Long): FoodEntity?

    @Query("SELECT * FROM food WHERE isSelected = 1")
    fun getCartFood(): Flow<List<FoodEntity>>

}