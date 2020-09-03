package com.albuquerque.menuly.app.remote

import com.albuquerque.menuly.app.data.dto.MenuDTO
import retrofit2.http.GET

interface MenulyAPI {

    @GET("menu")
    suspend fun fetchMenu(): MenuDTO

}