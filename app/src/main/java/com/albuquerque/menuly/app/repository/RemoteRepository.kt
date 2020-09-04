package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.data.dto.CategoryDTO

interface RemoteRepository {

    suspend fun fetchMenu(): Result<List<CategoryDTO>>

}