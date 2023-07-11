package com.example.androidcountersample.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * LiveDataの値が変更されるまで待つ
 */
suspend fun <T> waitForLiveDataChange(
    expected: T,
    liveData: LiveData<T>,
    timeout: Long = 3000
) {
    val latch = CountDownLatch(1)
    val observer = object: Observer<T> {
        override fun onChanged(value: T) {
            if (value == expected) {
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
    }
    liveData.observeForever(observer)

    withContext(Dispatchers.IO) {
        latch.await(timeout, TimeUnit.MILLISECONDS)
    }
}
