package com.dcoders.shoppinglistapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewmodel:ViewModel() {

    private val _count= mutableStateOf(0)

    val count:MutableState<Int> =_count

    fun incrementCount(){
        _count.value++
    }
    fun decrementCount(){
        _count.value--
    }


}