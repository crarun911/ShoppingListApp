package com.dcoders.shoppinglistapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewmodel:ViewModel() {

    private val counterRepository=CounterRepository()

    private var _count= mutableStateOf(counterRepository.getCounter().count)

    val count:MutableState<Int> =_count

    fun incrementCount(){
        counterRepository.incrementCounter()
        _count.value=counterRepository.getCounter().count
    }
    fun decrementCount(){
        counterRepository.decrementCounter()
        _count.value=counterRepository.getCounter().count
    }


}