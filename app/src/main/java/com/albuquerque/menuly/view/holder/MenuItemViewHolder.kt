package com.albuquerque.menuly.view.holder

import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.menuly.databinding.ItemMenuBodyBinding

class MenuItemViewHolder(binding: ViewDataBinding): MenuViewHolder(binding) {

    override fun bind(item: MenuUI) {
        with(binding as ItemMenuBodyBinding) {
            menu = item

            root.setOnClickListener {
                Toast.makeText(root.context, item.name, Toast.LENGTH_LONG).show()
            }
        }
    }

}