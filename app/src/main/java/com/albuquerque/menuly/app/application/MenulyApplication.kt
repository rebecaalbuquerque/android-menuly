package com.albuquerque.menuly.app.application

import android.app.Application
import com.albuquerque.menuly.app.data.AppDatabase
import com.albuquerque.menuly.app.repository.*
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
                single { get<AppDatabase>().menuDAO }
            }

            val repositoryModule = module {
                factory<RemoteRepository> { RemoteRepositoryImpl() }
                factory<LocalRepository> { LocalRepositoryImpl(menuDao = get()) }
                factory<Repository> { RepositoryImpl(remote = get(), local = get()) }
            }

            val useCaseModule = module {
                //factory { UseCase(repository = get()) }
            }

            val viewModelModule = module {
                //viewModel {  }
            }

            modules(listOf(databaseModule, repositoryModule, useCaseModule, viewModelModule))

        }

    }

}