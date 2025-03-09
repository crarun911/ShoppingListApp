package com.dcoders.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


data class ListItem(val id:Int,
                    var name:String,
                    var quantity:Int,
                    var isEditable:Boolean=false)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(){
    var shoppingList= remember { mutableListOf<ListItem>() }
    var showDialogue by remember { mutableStateOf(false)   }
    var itemName by remember { mutableStateOf("")    }
    var itemQuantity by remember { mutableStateOf("")    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialogue=true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp))
        {
            items(shoppingList){

            }
        }
    }
    if (showDialogue){
       AlertDialog(
           onDismissRequest = { showDialogue=false },
           title ={ Text(text = "Hello Sample Dialogue")},
           text = {
                  Column(){

                      OutlinedTextField(modifier = Modifier.padding(8.dp), value = itemName, onValueChange = {
                          itemName=it
                      })
                      OutlinedTextField(modifier = Modifier.padding(8.dp),value = itemQuantity, onValueChange = {
                          itemQuantity=it
                      })
                  }
           },
           confirmButton =  {
               Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                   horizontalArrangement = Arrangement.SpaceEvenly
               ) {
                   Button(onClick = {

                       if (itemName.isNotBlank()){
                           val newItem=ListItem(id = shoppingList.size+1,
                               name = itemName,
                               quantity = itemQuantity.toInt()
                               )

                           shoppingList.add(newItem)
                           showDialogue=false
                           itemName=""
                       }
                   }) {
                       Text(text = "Ok")
                   }
                   Button(onClick = { showDialogue=false}) {
                       Text(text = "Cancel")
                   }
               }
          }

       )

    }
}
