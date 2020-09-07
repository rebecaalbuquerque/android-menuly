package com.albuquerque.data.dto

import com.google.gson.annotations.SerializedName

data class RestaurantDTO(
    var id: Long,

    var name: String? = null,

    @SerializedName("delivery_fee")
    var deliveryFee: Double? = null,

    @SerializedName("minimum_order_price")
    var minimumOrderPrice: Double? = null
)