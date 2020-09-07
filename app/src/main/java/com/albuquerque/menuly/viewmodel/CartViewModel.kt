package com.albuquerque.menuly.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.core.util.Event
import com.albuquerque.core.util.SingleLiveEvent
import com.albuquerque.core.view.mediator.SingleMediatorLiveData
import com.albuquerque.core.viewmodel.BaseViewModel
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.domain.usecase.GetCartFoodUseCase
import com.albuquerque.menuly.extensions.toBrazilianCurrency
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartFoodUseCase: GetCartFoodUseCase
): BaseViewModel() {

    private val _cart = SingleMediatorLiveData<List<MenuUI>>()
    val cart = _cart as LiveData<List<MenuUI>>

    private val _total = MutableLiveData<String>()
    val total = _total as LiveData<String>

    val onEmpty = SingleLiveEvent<Event<Any>>()

    init {
        viewModelScope.launch {
            getCartFoodUseCase.invoke()
                .collect { list ->

                    if(list.isEmpty())
                        onEmpty.call()

                    _total.postValue(list.sumByDouble { it.price }.toBrazilianCurrency())

                    _cart.emit(liveData { emit(list) })
                }
        }
    }

}