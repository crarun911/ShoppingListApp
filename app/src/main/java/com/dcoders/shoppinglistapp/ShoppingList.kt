package com.dcoders.shoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


data class ListItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditable: Boolean = false
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp() {
    var shoppingList by remember { mutableStateOf(listOf<ListItem>()) }
    var showDialogue by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialogue = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            items(shoppingList) { item ->
                if (item.isEditable) {
                    ShoppingItemEdit(selectedItem = item, onEditComplete = { editName, quantity ->

                            shoppingList= shoppingList.map { it.copy(isEditable = false) }
                            val editedItem=shoppingList.find { it.id==item.id}
                            editedItem?.let {
                                it.name=editName
                                it.quantity=quantity
                            }

                    })
                }else{
                    SelectedItem(item = item, onEditClicked = {
                        shoppingList= shoppingList.map { it.copy(isEditable = it.id==item.id) }.toMutableList()
                    }, onDeleteClick = {
                        shoppingList= (shoppingList-item).toMutableList()
                    })
                }
            }
        }
    }
    if (showDialogue) {
        AlertDialog(
            onDismissRequest = { showDialogue = false },
            title = { Text(text = "Hello Sample Dialogue") },
            text = {
                Column() {

                    OutlinedTextField(
                        modifier = Modifier.padding(8.dp),
                        value = itemName,
                        onValueChange = {
                            itemName = it
                        })
                    OutlinedTextField(
                        modifier = Modifier.padding(8.dp),
                        value = itemQuantity,
                        onValueChange = {
                            itemQuantity = it
                        })
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        if (itemName.isNotBlank()) {
                            val newItem = ListItem(
                                id = shoppingList.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )
                            shoppingList=shoppingList+newItem
                            showDialogue = false
                            itemName = ""
                        }
                    }) {
                        Text(text = "Ok")
                    }
                    Button(onClick = { showDialogue = false }) {
                        Text(text = "Cancel")
                    }
                }
            }

        )

    }
}

@Composable
fun SelectedItem(
    item: ListItem,
    onEditClicked: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(BorderStroke(2.dp, Color.Gray), shape = RoundedCornerShape(20))
    ) {
        Text(text = item.name, modifier = Modifier.padding(4.dp))
        Text(text = "Qty:${item.quantity.toString()}", modifier = Modifier.padding(4.dp))
        Row {
            IconButton(onClick = { onEditClicked() }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = { onDeleteClick() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }

    }
}

@Composable
fun ShoppingItemEdit(selectedItem: ListItem, onEditComplete: (String, Int) -> Unit) {
    var itemName by remember { mutableStateOf(selectedItem.name) }
    var itemQuantity by remember { mutableStateOf(selectedItem.quantity.toString()) }
    var isEditable by remember { mutableStateOf(selectedItem.isEditable) }
    Row {
        Column {
            BasicTextField(
                value = itemName,
                onValueChange = { itemName = it },
                Modifier.padding(5.dp)
            )
            BasicTextField(
                value = itemQuantity,
                onValueChange = { itemQuantity = it },
                Modifier.padding(5.dp)
            )
        }
        Button(onClick = {
            isEditable = false
            onEditComplete(itemName, itemQuantity.toIntOrNull() ?: 1)
        }) {
            Text(text = "Update")
        }

    }


}
