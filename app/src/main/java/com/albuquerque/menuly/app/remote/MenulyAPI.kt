package com.albuquerque.menuly.app.remote

import com.albuquerque.menuly.app.data.dto.CategoryDTO
import retrofit2.http.GET

interface MenulyAPI {

    @GET("menu")
    suspend fun fetchMenu(): List<CategoryDTO>

}