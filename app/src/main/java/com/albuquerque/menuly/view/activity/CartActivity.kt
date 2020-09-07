package com.albuquerque.menuly.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.albuquerque.core.view.activity.BaseActivity
import com.albuquerque.menuly.R
import com.albuquerque.menuly.adapter.MenuAdapter
import com.albuquerque.menuly.databinding.ActivityCartBinding
import com.albuquerque.menuly.extensions.setVisible
import com.albuquerque.menuly.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartActivity : BaseActivity() {

    private lateinit var binding: ActivityCartBinding
    private val cartViewModel: CartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)

        setupView()
        setupDatabinding()
        subscribeUI()
    }

    private fun subscribeUI() {
        with(cartViewModel) {
            onEmpty.observe(this@CartActivity) {
                layoutEmpty.setVisible()
            }
        }
    }

    private fun setupDatabinding() {
        with(binding) {
            lifecycleOwner = this@CartActivity
            viewModel = this@CartActivity.cartViewModel
            executePendingBindings()
        }
    }

    private fun setupView() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.action_cart_title)
        }

        rvCart.adapter = MenuAdapter {}
    }

}