package com.albuquerque.domain.remote

import com.albuquerque.data.dto.CategoryDTO
import retrofit2.http.GET

interface MenulyAPI {

    @GET("menu")
    suspend fun fetchMenu(): List<CategoryDTO>

}