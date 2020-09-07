package com.albuquerque.menuly.viewmodel

import androidx.lifecycle.*
import com.albuquerque.core.mediator.SingleMediatorLiveData
import com.albuquerque.core.view.util.ViewState
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
        viewState.value = ViewState.LoadingState

        viewModelScope.launch {
            getMenuUseCase.invokeFromApi().collect { result ->
                viewState.value = ViewState.Idle

                result
                    .onSuccess {

                        if(_menu.value.isNullOrEmpty() && it.isEmpty())
                            viewState.value = ViewState.EmptyState

                    }
                    .onFailure {

                        if(_menu.value.isNullOrEmpty())
                            viewState.value = ViewState.EmptyState
                        else
                            viewState.value = ViewState.ErrorState(it.message)

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