package com.albuquerque.menuly.app.viewmodel

import com.albuquerque.menuly.app.usecase.GetMenuUseCase
import com.albuquerque.menuly.core.viewmodel.BaseViewModel

class MenuViewModel(
    private val getMenuUseCase: GetMenuUseCase
): BaseViewModel() {



}