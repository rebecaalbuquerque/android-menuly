package com.albuquerque.menuly.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.core.util.Event
import com.albuquerque.core.view.mediator.SingleMediatorLiveData
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

    val onEmpty = MutableLiveData<Event<Any>>()
    val onLessThanTheMinimum = MutableLiveData<Event<Double>>()
    val onCompletedOrder = MutableLiveData<Event<Double>>()

    init {
        viewModelScope.launch {
            getCartFoodUseCase.invoke()
                .collect { list ->

                    if(list.isEmpty())
                        onEmpty.postValue(Event(Any()))

                    _total.postValue(list.sumByDouble { it.price })

                    _cart.emit(liveData { emit(list) })
                }
        }
    }

    fun tryCheckOut() {
        onShowLoading.postValue(Event(Any()))

        viewModelScope.launch {

            getRestaurantUseCase.invoke()
                .onSuccess { restaurant ->

                    total.value?.let { value ->

                         if(value < restaurant.minimumOrderPrice) {
                             onHideLoading.postValue(Event(Any()))
                             onLessThanTheMinimum.postValue(Event(restaurant.minimumOrderPrice))
                         } else {
                            checkOut()
                         }
                    }

                }
                .onFailure {
                    onHideLoading.postValue(Event(Any()))
                    onSnackBarError.postValue(Event(it.message))
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
                onHideLoading.postValue(Event(Any()))
                total.value?.let {
                    onCompletedOrder.postValue(Event(it))
                }
            }
            .onFailure {
                onHideLoading.postValue(Event(Any()))
                onSnackBarError.postValue(Event(it.message))
            }
    }

}