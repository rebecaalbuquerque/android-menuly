package com.albuquerque.domain.usecase

import com.albuquerque.data.toListMenuUI
import com.albuquerque.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetCategoriesUseCase(
    private val repository: Repository
) {

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

}