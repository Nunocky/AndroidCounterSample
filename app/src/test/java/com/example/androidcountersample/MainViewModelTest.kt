package com.example.androidcountersample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.androidcountersample.util.waitForLiveDataChange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testIncrementCountAndValueRestoring() = runTest {
        val savedStateHandle = SavedStateHandle()
        val oldViewModel = MainViewModel(savedStateHandle)

        // 初期値は 0
        waitForLiveDataChange("0", oldViewModel.count)
        assertEquals("0", oldViewModel.count.value)

        // incrementCountを実行
        oldViewModel.incrementCount()
        oldViewModel.incrementCount()
        oldViewModel.incrementCount()

        // 値が 3に更新される
        waitForLiveDataChange("3", oldViewModel.count)
        assertEquals("3", oldViewModel.count.value)

        // ViewModelの再生成 → 値は 3 で復元される
        val newViewModel = MainViewModel(savedStateHandle)
        waitForLiveDataChange("3", newViewModel.count)
        assertEquals("3", newViewModel.count.value)
    }
}