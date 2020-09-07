package com.albuquerque.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MenuEntity(

    @Embedded val category: CategoryEntity,

    @Relation(parentColumn = "categoryId", entityColumn = "categoryId", entity = FoodEntity::class)
    val food: List<FoodEntity>

)