package com.albuquerque.domain.usecase

import com.albuquerque.data.toMenuUI
import com.albuquerque.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetCartFoodUseCase(
    private val repository: Repository
) {

    suspend fun invoke() = flow {
        emitAll(
            repository.getCartFood()
                .map { list ->
                    list.map { it.toMenuUI() }
                }.flowOn(Dispatchers.IO)
        )
    }

}