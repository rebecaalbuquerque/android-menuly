package com.albuquerque.menuly.core.view.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(var binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root), BindView<T> {

    abstract override fun bind(item: T)

}