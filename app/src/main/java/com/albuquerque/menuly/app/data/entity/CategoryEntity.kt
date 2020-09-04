package com.albuquerque.menuly.app.data.entity

import androidx.room.Entity

@Entity(tableName = "category")
class CategoryEntity(
    var categoryId: Long,
    var name: String
)