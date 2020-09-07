package com.albuquerque.domain.repository

import com.albuquerque.core.extensions.capitalizeSentence
import com.albuquerque.data.dto.CategoryDTO
import com.albuquerque.data.dto.RestaurantDTO
import com.albuquerque.domain.remote.MenulyAPI
import com.albuquerque.domain.remote.Remote

class RemoteRepositoryImpl: Remote(), RemoteRepository {

    private val menulyApi by lazy { getRetrofitBuilder().create(MenulyAPI::class.java) }

    override suspend fun fetchMenu(): Result<List<CategoryDTO>> {
        return runRequest {
            menulyApi.fetchMenu()
        }.onSuccess { list ->
            list.map {
                it.name = it.name.capitalizeSentence()
                it
            }
        }
    }

    override suspend fun fetchRestaurant(): Result<RestaurantDTO> {
        return runRequest { menulyApi.fetchRestaurant() }
    }

}