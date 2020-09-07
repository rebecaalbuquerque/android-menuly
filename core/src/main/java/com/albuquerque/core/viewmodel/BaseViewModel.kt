package com.albuquerque.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.albuquerque.core.util.Event

abstract class BaseViewModel: ViewModel() {

    val onSnackBarError = MutableLiveData<Event<String?>>()
    val onLayoutError = MutableLiveData<Event<Any>>()
    val onShowLoading = MutableLiveData<Event<Any>>()
    val onHideLoading = MutableLiveData<Event<Any>>()

}