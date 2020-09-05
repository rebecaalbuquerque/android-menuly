package com.albuquerque.menuly.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey var foodId: Long,
    var name: String,
    var description: String?,
    var price: Double,
    var imageUrl: String?,
    var categoryId: Long
)