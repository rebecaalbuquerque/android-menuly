package com.albuquerque.menuly.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.albuquerque.core.view.activity.BaseActivity
import com.albuquerque.core.view.util.ViewState
import com.albuquerque.menuly.R
import com.albuquerque.menuly.adapter.MenuAdapter
import com.albuquerque.menuly.databinding.ActivityMenuBinding
import com.albuquerque.menuly.extensions.setGone
import com.albuquerque.menuly.extensions.setVisible
import com.albuquerque.menuly.extensions.showSnackbar
import com.albuquerque.menuly.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.layout_menu_empty.*
import kotlinx.android.synthetic.main.layout_menu_loading.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuActivity : BaseActivity() {

    private lateinit var binding: ActivityMenuBinding
    private val menuViewModel: MenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)

        setupView()
        subscribeUI()
        setupDataBinding()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.action_cart -> {
                startActivity(
                    Intent(this, CartActivity::class.java)
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUI() {

        with(menuViewModel) {

            viewState.observe(this@MenuActivity) { viewState ->

                when(viewState) {

                    is ViewState.Idle -> hideLoading()

                    is ViewState.LoadingState -> {
                        layoutEmpty.setGone()
                        showLoading()
                    }

                    is ViewState.EmptyState -> layoutEmpty.setVisible()

                    is ViewState.ErrorState -> {
                        viewState.message?.let {
                            container.showSnackbar(it)
                        }

                    }
                }

            }

        }

    }

    private fun setupView() {
        rvMenu.adapter = MenuAdapter {
            menuViewModel.selectFood(it)
        }

        tryAgain.setOnClickListener {
            menuViewModel.getMenu()
        }

        swipeRefresh.setOnRefreshListener {
            menuViewModel.getMenu()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setupDataBinding() {
        with(binding) {
            lifecycleOwner = this@MenuActivity
            viewModel = this@MenuActivity.menuViewModel
            executePendingBindings()
        }
    }

    private fun showLoading() {
        rvMenu.setGone()
        layoutLoading.setVisible()

        val animation = AlphaAnimation(1.0f, 0.6f).apply {
            this.duration = 900
            this.startOffset = 20
            this.repeatMode = Animation.REVERSE
            this.repeatCount = Animation.INFINITE
        }

        listOf(header, loadingImage1, loadingName1, loadingDescription1, loadingDescription12, loadingPrice1, loadingImage2, loadingName2, loadingDescription2, loadingDescription22, loadingPrice2).forEach {
            it.startAnimation(animation)
        }
    }

    private fun hideLoading() {
        listOf(header, loadingImage1, loadingName1, loadingDescription1, loadingDescription12, loadingPrice1, loadingImage2, loadingName2, loadingDescription2, loadingDescription22, loadingPrice2).forEach {
            it.animation?.cancel()
        }

        layoutLoading.setGone()
        rvMenu.setVisible()
    }

}