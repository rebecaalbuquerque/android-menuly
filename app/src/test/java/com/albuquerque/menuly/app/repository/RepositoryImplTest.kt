package com.albuquerque.menuly.app.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RepositoryImplTest : BaseTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `test get categories from db`() =
        runBlocking {
            categoryDao.insertAll(listOf(FakeDataLocal.cat1, FakeDataLocal.cat2))

            repository.getCategoriesFromDb()
                .take(1)
                .collect {
                    assertThat(it).isEqualTo(listOf(FakeDataLocal.cat1, FakeDataLocal.cat2))
                }
        }

    @Test
    fun `test get succesfully menu from api`() =
        runBlocking {
            val result = repository.getMenuFromApi()
            assertThat(result.isSuccess).isTrue()
        }

    @After
    fun teardown() {
        // At the end of all tests, query executor should be idle.
        countingTaskExecutorRule.drainTasks(500, TimeUnit.MILLISECONDS)
        assertThat(countingTaskExecutorRule.isIdle).isTrue()
    }

}