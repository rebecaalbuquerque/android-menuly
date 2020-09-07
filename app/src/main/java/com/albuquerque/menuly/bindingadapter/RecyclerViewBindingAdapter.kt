package com.albuquerque.menuly.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.albuquerque.core.adapter.BaseAdapter
import com.albuquerque.core.view.holder.BaseViewHolder

@BindingAdapter("app:items")
fun <T> setItems(recyclerView: RecyclerView, items: List<T>?) {
    items?.let {
        (recyclerView.adapter as? BaseAdapter<T, BaseViewHolder<T>>)?.refresh(items)
    }
}