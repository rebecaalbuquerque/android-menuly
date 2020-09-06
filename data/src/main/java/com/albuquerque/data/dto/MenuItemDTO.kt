package com.albuquerque.data.dto

import com.google.gson.annotations.SerializedName

data class MenuItemDTO(
    var id: Long,

    var name: String,

    var price: Double,

    @SerializedName("image_url")
    var imageUrl: String
)