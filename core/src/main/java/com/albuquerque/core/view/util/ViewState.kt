package com.albuquerque.core.view.util

sealed class ViewState {
    object Idle : ViewState()
    object LoadingState : ViewState()
    object EmptyState : ViewState()
    class ErrorState(val message: String?) : ViewState()
}