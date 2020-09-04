package com.albuquerque.menuly.app.data.dto


data class CategoryDTO(
    var id: Long,
    var name: String,
    var items: List<FoodDTO>
)