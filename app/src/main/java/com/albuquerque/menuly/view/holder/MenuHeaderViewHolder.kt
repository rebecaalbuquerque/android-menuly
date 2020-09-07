package com.albuquerque.menuly.view.holder

import androidx.databinding.ViewDataBinding
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.menuly.databinding.ItemMenuHeaderBinding

class MenuHeaderViewHolder(binding: ViewDataBinding): MenuViewHolder(binding) {

    override fun bind(item: MenuUI) {
        with(binding as ItemMenuHeaderBinding) {
            menu = item
        }
    }

}