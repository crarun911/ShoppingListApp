package com.dcoders.shoppinglistapp

import androidx.compose.runtime.mutableStateOf

data class CounterModel(var count:Int)


class CounterRepository(){

    private var _count= CounterModel(0)

    fun getCounter()=_count

    fun incrementCounter(){
        _count.count++
    }
    fun decrementCounter(){
        _count.count--
    }



}