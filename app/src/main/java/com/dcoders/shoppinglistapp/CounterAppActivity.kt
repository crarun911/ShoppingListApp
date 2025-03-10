package com.dcoders.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

class CounterAppActivity:ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                CounterApp()
            }
    }


}

@Composable
fun CounterApp(){

    var count= remember {
        mutableStateOf(0)
    }
    fun incrementCount(){
        count.value++
    }
    fun decrementCount(){
        count.value--
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "count:${count.value?:0}", fontSize = 18.sp)
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { incrementCount() }) {
                Text(text = "increase")
            }
            Button(onClick = { decrementCount() }) {
                Text(text = "decrease")
            }
        }

    }





}