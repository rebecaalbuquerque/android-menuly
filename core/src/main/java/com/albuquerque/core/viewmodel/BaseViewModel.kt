package com.albuquerque.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.albuquerque.core.view.util.ViewState

abstract class BaseViewModel: ViewModel() {

    val viewState = MutableLiveData<ViewState>()

}