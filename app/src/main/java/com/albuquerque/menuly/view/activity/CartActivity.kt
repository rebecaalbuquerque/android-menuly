package com.albuquerque.menuly.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.albuquerque.core.view.activity.BaseActivity
import com.albuquerque.core.view.util.ViewState
import com.albuquerque.menuly.R
import com.albuquerque.menuly.adapter.MenuAdapter
import com.albuquerque.menuly.databinding.ActivityCartBinding
import com.albuquerque.menuly.extensions.setGone
import com.albuquerque.menuly.extensions.setVisible
import com.albuquerque.menuly.extensions.showSnackbar
import com.albuquerque.menuly.extensions.toBrazilianCurrency
import com.albuquerque.menuly.viewmodel.CartViewModel
import com.albuquerque.menuly.widget.*
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

            viewState.observe(this@CartActivity) { viewState ->
                when(viewState) {
                    is ViewState.Idle -> orderProgress.setGone()
                    is ViewState.LoadingState -> orderProgress.setVisible()
                    is ViewState.EmptyState -> layoutEmpty.setVisible()
                    is ViewState.ErrorState -> {
                        orderProgress.setGone()
                        viewState.message?.let { container.showSnackbar(it) }
                    }
                }
            }

            onCompletedOrder.observe(this@CartActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    BottomDialog.build(this@CartActivity)
                        .icon(R.drawable.ic_success)
                        .title(getString(R.string.cart_order_title_sucess))
                        .body(getString(R.string.cart_order_body_sucess, it.toBrazilianCurrency()))
                        .onPositive(getString(R.string.ok)){
                            onBackPressed()
                            clearCart()
                        }
                }
            }

            onLessThanTheMinimum.observe(this@CartActivity) { event ->
                event.getContentIfNotHandled()?.let {
                    BottomDialog.build(this@CartActivity)
                        .icon(R.drawable.ic_error)
                        .title(getString(R.string.cart_order_title_error))
                        .body(getString(R.string.cart_order_body_error, it.toBrazilianCurrency()))
                        .onPositive(getString(R.string.ok))
                }
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