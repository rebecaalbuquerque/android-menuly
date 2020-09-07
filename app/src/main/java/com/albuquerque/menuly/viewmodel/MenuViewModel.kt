package com.albuquerque.menuly.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.albuquerque.core.util.Event
import com.albuquerque.core.view.mediator.SingleMediatorLiveData
import com.albuquerque.domain.usecase.GetMenuUseCase
import com.albuquerque.core.viewmodel.BaseViewModel
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.domain.usecase.SelectFoodUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MenuViewModel(
    private val getMenuUseCase: GetMenuUseCase,
    private val selectFoodUseCase: SelectFoodUseCase
): BaseViewModel() {

    private val _menu = SingleMediatorLiveData<List<MenuUI>>().apply {
        viewModelScope.launch {
            this@apply.emit(getMenuUseCase.invokeFromDb().asLiveData().distinctUntilChanged())
        }
    }

    val menu = _menu as LiveData<List<MenuUI>>

    init {
        getMenu()
    }

    fun getMenu() {
        onShowLoading.call()

        viewModelScope.launch {
            getMenuUseCase.invokeFromApi().collect { result ->
                onHideLoading.call()

                result.onFailure {

                    if(_menu.value.isNullOrEmpty())
                        onLayoutError.call()
                    else
                        onSnackBarError.postValue(Event(it.message))

                }

            }
        }
    }

    fun selectFood(idFood: Long) {
        viewModelScope.launch {
            selectFoodUseCase.invoke(idFood)
        }
    }

}