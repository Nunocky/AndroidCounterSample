package com.example.androidcountersample

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _count = savedStateHandle.getStateFlow("count", 0)
    val count = _count.map { it.toString() }.asLiveData()

    fun incrementCount() {
        savedStateHandle["count"] = _count.value + 1
    }

//    private val _count = MutableStateFlow(0)
//    val count = _count.map { it.toString() }.asLiveData()
//
//    fun incrementCount() {
//        _count.value += 1
//    }
}