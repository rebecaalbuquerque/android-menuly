package com.albuquerque.menuly.application

import android.app.Application
import com.albuquerque.data.AppDatabase
import com.albuquerque.domain.repository.*
import com.albuquerque.domain.usecase.*
import com.albuquerque.menuly.viewmodel.CartViewModel
import com.albuquerque.menuly.viewmodel.MenuViewModel
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MenulyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupStetho()
        setupRoom()
        setupKoin()

    }

    private fun setupStetho() {
        Stetho.initialize(
            Stetho.newInitializerBuilder(this).apply {
                enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this@MenulyApplication))
            }.build()
        )
    }

    private fun setupRoom() {
        AppDatabase.getInstance(this)
    }

    private fun setupKoin() {

        startKoin {
            androidContext(this@MenulyApplication)

            val databaseModule = module {
                single { AppDatabase.getInstance(get()) }
                single { get<AppDatabase>().categoryDAO }
                single { get<AppDatabase>().foodDAO }
            }

            val repositoryModule = module {
                factory<RemoteRepository> { RemoteRepositoryImpl() }
                factory<LocalRepository> { LocalRepositoryImpl(categoryDao = get(), foodDao = get()) }
                factory<Repository> { RepositoryImpl(remote = get(), local = get()) }
            }

            val useCaseModule = module {
                factory { GetMenuUseCase(repository = get()) }
                factory { GetRestaurantUseCase(repository = get()) }
                factory { CheckOutUseCase(repository = get()) }
                factory { SelectFoodUseCase(repository = get()) }
                factory { GetCartFoodUseCase(repository = get()) }
                factory { ClearCartUseCase(repository = get()) }
            }

            val viewModelModule = module {
                viewModel { MenuViewModel(getMenuUseCase = get(), selectFoodUseCase = get()) }
                viewModel { CartViewModel(
                    getCartFoodUseCase = get(),
                    checkOutUseCase = get(),
                    getRestaurantUseCase = get(),
                    clearCartUseCase = get()
                ) }
            }

            modules(listOf(databaseModule, repositoryModule, useCaseModule, viewModelModule))

        }

    }

}