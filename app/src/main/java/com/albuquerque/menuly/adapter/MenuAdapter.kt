package com.albuquerque.menuly.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.albuquerque.core.adapter.BaseAdapter
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.menuly.R
import com.albuquerque.menuly.databinding.ItemMenuBodyBinding
import com.albuquerque.menuly.databinding.ItemMenuHeaderBinding
import com.albuquerque.menuly.view.holder.MenuHeaderViewHolder
import com.albuquerque.menuly.view.holder.MenuItemViewHolder
import com.albuquerque.menuly.view.holder.MenuViewHolder


class MenuAdapter(
    private val onClick: (id: Long) -> Unit
) : BaseAdapter<MenuUI, MenuViewHolder>() {

    companion object {
        const val MENU_HEADER = 0
        const val MENU_BODY = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItemAt(position).isHeader) MENU_HEADER else MENU_BODY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {

        return when (viewType) {
            MENU_HEADER -> {
                val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_menu_header,
                    parent,
                    false
                )

                MenuHeaderViewHolder(binding as ItemMenuHeaderBinding)
            }

            else -> {

                val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_menu_body,
                    parent,
                    false
                )

                MenuItemViewHolder(binding as ItemMenuBodyBinding)
            }
        }

    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        when(holder) {
            is MenuItemViewHolder -> holder.bind(getItemAt(position), onClick)
            else -> (holder as MenuHeaderViewHolder).bind(getItemAt(position))
        }
    }

}
