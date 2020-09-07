package com.albuquerque.menuly.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.core.util.Event
import com.albuquerque.core.mediator.SingleMediatorLiveData
import com.albuquerque.core.view.util.ViewState
import com.albuquerque.core.viewmodel.BaseViewModel
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.domain.usecase.CheckOutUseCase
import com.albuquerque.domain.usecase.ClearCartUseCase
import com.albuquerque.domain.usecase.GetCartFoodUseCase
import com.albuquerque.domain.usecase.GetRestaurantUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartFoodUseCase: GetCartFoodUseCase,
    private val getRestaurantUseCase: GetRestaurantUseCase,
    private val checkOutUseCase: CheckOutUseCase,
    private val clearCartUseCase: ClearCartUseCase
): BaseViewModel() {

    private val _cart = SingleMediatorLiveData<List<MenuUI>>()
    val cart = _cart as LiveData<List<MenuUI>>

    private val _total = MutableLiveData<Double>()
    val total = _total as LiveData<Double>

    val onLessThanTheMinimum = MutableLiveData<Event<Double>>()
    val onCompletedOrder = MutableLiveData<Event<Double>>()

    init {
        viewModelScope.launch {
            getCartFoodUseCase.invoke()
                .collect { list ->

                    if(list.isEmpty())
                        viewState.value = ViewState.EmptyState

                    _total.postValue(list.sumByDouble { it.price })

                    _cart.emit(liveData { emit(list) })
                }
        }
    }

    fun tryCheckOut() {
        viewState.value = ViewState.LoadingState

        viewModelScope.launch {

            getRestaurantUseCase.invoke()
                .onSuccess { restaurant ->

                    total.value?.let { value ->

                         if(value < restaurant.minimumOrderPrice) {
                             viewState.value = ViewState.Idle
                             onLessThanTheMinimum.postValue(Event(restaurant.minimumOrderPrice))
                         } else {
                            checkOut()
                         }
                    }

                }
                .onFailure {
                    viewState.value = ViewState.ErrorState(it.message)
                }

        }

    }

    fun clearCart() {
        viewModelScope.launch {
            clearCartUseCase.invoke()
        }
    }

    private suspend fun checkOut() {
        checkOutUseCase.invoke()
            .onSuccess {
                Log.d("Pedido", "${cart.value}")
                viewState.value = ViewState.Idle
                total.value?.let {
                    onCompletedOrder.postValue(Event(it))
                }
            }
            .onFailure {
                viewState.value = ViewState.ErrorState(it.message)
            }
    }

}