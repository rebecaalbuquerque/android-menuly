package com.albuquerque.menuly.app.usecase

import com.albuquerque.menuly.app.data.toListMenuUI
import com.albuquerque.menuly.app.data.ui.MenuUI
import com.albuquerque.menuly.app.extensions.asFlow
import com.albuquerque.menuly.app.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetMenuUseCase(
    private val repository: Repository
) {

    /**
     * Execute retrieve from database
     * */
    suspend fun invokeFromDb() = flow {
        emitAll(
            repository.getMenuFromDb()
                .map { list ->
                    list
                        .map { it.toListMenuUI() }
                        .flatten()
                }.flowOn(Dispatchers.IO)
        )
    }

    /**
     * Execute retrieve from API
     * */
    suspend fun invokeFromApi(): Flow<Result<List<MenuUI>>> = flow {
        emitAll(
            repository.getMenuFromApi()
                .map {
                    it
                        .map { category ->
                            category.toListMenuUI()
                        }
                        .flatten()
                }
                .asFlow().flowOn(Dispatchers.IO)
        )

    }

}