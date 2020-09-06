package com.albuquerque.menuly.viewmodel

import com.albuquerque.domain.usecase.GetMenuUseCase
import com.albuquerque.core.viewmodel.BaseViewModel

class MenuViewModel(
    private val getMenuUseCase: GetMenuUseCase
): BaseViewModel() {



}