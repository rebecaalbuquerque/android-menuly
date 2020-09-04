package com.albuquerque.menuly.app.data.entity

import androidx.room.Entity

@Entity(tableName = "food")
class FoodEntity(
    var foodId: Long,
    var name: String,
    var description: String?,
    var price: Double,
    var imageUrl: String?,
    var categoryId: Long
)