package com.dcoders.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.lifecycle.ViewModel

class CounterAppActivity:ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                val counterViewmodel:CounterViewmodel by viewModels()
                CounterApp(counterViewmodel)
            }
    }


}

@Composable
fun CounterApp(counterViewmodel: CounterViewmodel){


    fun incrementCount(){
        counterViewmodel.count.value++
    }
    fun decrementCount(){
        counterViewmodel.count.value--
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "count:${counterViewmodel.count.value?:0}", fontSize = 18.sp)
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { counterViewmodel.incrementCount() }) {
                Text(text = "increase")
            }
            Button(onClick = { counterViewmodel.decrementCount() }) {
                Text(text = "decrease")
            }
        }

    }





}