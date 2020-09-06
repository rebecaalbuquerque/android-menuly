package com.albuquerque.core.adapter

import androidx.recyclerview.widget.RecyclerView
import com.albuquerque.core.view.holder.BaseViewHolder


abstract class BaseAdapter<T, Holder : BaseViewHolder<T>> : RecyclerView.Adapter<Holder>() {

    private var items: MutableList<T> = mutableListOf()

    open fun refresh(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    open fun getItemAt(position: Int): T = items[position]

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}