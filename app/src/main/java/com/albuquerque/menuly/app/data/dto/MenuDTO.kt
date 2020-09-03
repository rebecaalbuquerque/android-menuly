package com.albuquerque.menuly.app.data.dto

data class MenuDTO(
    var id: Long,
    var name: String,
    var items: List<MenuItemDTO> = listOf()
)