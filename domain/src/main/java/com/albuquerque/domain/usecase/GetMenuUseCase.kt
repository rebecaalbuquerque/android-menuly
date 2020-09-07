package com.albuquerque.domain.usecase

import com.albuquerque.data.toListMenuUI
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.domain.extensions.asFlow
import com.albuquerque.domain.repository.Repository
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
                .map { list ->
                    list
                        .map { it.toListMenuUI() }
                        .flatten()
                }
                .asFlow().flowOn(Dispatchers.IO)
        )

    }

}