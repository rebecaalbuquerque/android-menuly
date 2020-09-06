package com.albuquerque.domain.repository

import com.albuquerque.data.dto.CategoryDTO

interface RemoteRepository {

    suspend fun fetchMenu(): Result<List<CategoryDTO>>

}