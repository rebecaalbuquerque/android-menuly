package com.albuquerque.menuly.app.data.entity

import androidx.room.Embedded
import androidx.room.Relation

class MenuEntity(

    @Embedded val category: CategoryEntity,

    @Relation(parentColumn = "categoryId", entityColumn = "foodId")
    val food: List<FoodEntity>

)