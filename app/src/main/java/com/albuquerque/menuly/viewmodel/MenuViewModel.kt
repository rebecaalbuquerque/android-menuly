package com.albuquerque.menuly.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.core.util.Event
import com.albuquerque.core.view.mediator.SingleMediatorLiveData
import com.albuquerque.domain.usecase.GetMenuUseCase
import com.albuquerque.core.viewmodel.BaseViewModel
import com.albuquerque.data.ui.MenuUI
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MenuViewModel(
    private val getMenuUseCase: GetMenuUseCase
): BaseViewModel() {

    private val _menu = SingleMediatorLiveData<List<MenuUI>>().apply {
        viewModelScope.launch {
            this@apply.emit(getMenuUseCase.invokeFromDb().asLiveData())
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

}