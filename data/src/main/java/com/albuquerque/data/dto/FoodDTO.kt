package com.albuquerque.data.dto

import com.google.gson.annotations.SerializedName

data class FoodDTO(
    var id: Long,

    var name: String,

    var description: String? = null,

    var price: Double,

    @SerializedName("image_url")
    var imageUrl: String? = null
)