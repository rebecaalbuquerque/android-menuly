package com.albuquerque.domain.repository


import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.albuquerque.data.AppDatabase
import com.albuquerque.data.dao.CategoryDao
import com.albuquerque.data.dao.FoodDao
import com.albuquerque.domain.remote.MenulyAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

abstract class BaseTest {

    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    private lateinit var database: AppDatabase
    protected lateinit var categoryDao: CategoryDao
    protected lateinit var foodDao: FoodDao
    private lateinit var localRepository: LocalRepository

    private lateinit var mockWebServer: MockWebServer
    lateinit var menulyApi: MenulyAPI
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var remoteRepository: RemoteRepository

    protected lateinit var repository: Repository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        // database
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
            )
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .allowMainThreadQueries()
            .build()

        categoryDao = database.categoryDAO
        foodDao = database.foodDAO

        // service
        mockWebServer = MockWebServer().apply {
            dispatcher = MenulyRequestDispatcher()
        }
        mockWebServer.start()

        okHttpClient = buildOkhttpClient(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        menulyApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MenulyAPI::class.java)

        remoteRepository = RemoteRepositoryImpl()
        localRepository = LocalRepositoryImpl(categoryDao, foodDao)

        repository =
            RepositoryImpl(remoteRepository, localRepository)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        database.close()
    }

    private fun buildOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}