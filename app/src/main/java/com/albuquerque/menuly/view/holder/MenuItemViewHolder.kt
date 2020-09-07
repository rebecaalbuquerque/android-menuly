package com.albuquerque.menuly.view.holder

import androidx.databinding.ViewDataBinding
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.menuly.databinding.ItemMenuBodyBinding

class MenuItemViewHolder(binding: ViewDataBinding): MenuViewHolder(binding) {

    private lateinit var onClick: (id: Long) -> Unit

    override fun bind(item: MenuUI) {
        with(binding as ItemMenuBodyBinding) {
            menu = item

            root.setOnClickListener {
                onClick(item.id)
            }
        }
    }

    fun bind(item: MenuUI, onClick: (id: Long) -> Unit) {
        this.onClick = onClick
        bind(item)
    }

}