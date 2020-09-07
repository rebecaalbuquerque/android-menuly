package com.albuquerque.core.viewmodel

import androidx.lifecycle.ViewModel
import com.albuquerque.core.util.Event
import com.albuquerque.core.util.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {

    val onSnackBarError = SingleLiveEvent<Event<String?>>()
    val onLayoutError = SingleLiveEvent<Event<Any>>()
    val onShowLoading = SingleLiveEvent<Event<Any>>()
    val onHideLoading = SingleLiveEvent<Event<Any>>()

}