package com.albuquerque.menuly.app.data.ui

class MenuUI(
    var id: Long,
    var name: String,
    var description: String,
    var price: Double,
    var imageUrl: String? = null,
    var isHeader: Boolean = false
)