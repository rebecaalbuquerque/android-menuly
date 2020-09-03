package com.albuquerque.menuly.app.data.entity

import androidx.room.Entity

@Entity(tableName = "menu")
data class MenuEntity(
    var id: Long,
    var category: String
)