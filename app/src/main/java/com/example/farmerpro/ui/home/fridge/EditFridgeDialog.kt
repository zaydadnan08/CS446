package com.example.farmerpro.ui.home.fridge

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.components.GreyTextInput
import com.example.farmerpro.components.LocationText
import com.example.farmerpro.domain.model.FridgeItem

@Composable
fun EditFridgeDialog(
    closeDialog: () -> Unit,
    item: FridgeItem,
    viewModel: CommunityFridgeViewModel
) {
    var fridgeinventory by remember { mutableStateOf("") }
    if(item.fridgeinventory != null){
        fridgeinventory = item.fridgeinventory!!
    }
    AlertDialog(onDismissRequest = closeDialog, text = {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Edit Fridge Inventory", modifier = Modifier.padding(4.dp), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.fridge_name,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Location: ",
                        maxLines = 1,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    )
                    LocationText(location = item.location.orEmpty())
                }
                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(
                    value = fridgeinventory,
                    onValueChange = { fridgeinventory = it },
                    placeholder = "Fridge Inventory",
                    maxLines = 8,
                )
                Button(
                    onClick = {
                        item.id?.let {itemId ->
                            viewModel.updateFridge(itemId, fridgeinventory )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFA80000)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Update Inventory",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }, confirmButton = {}, dismissButton = {
        TextButton(
            onClick = closeDialog
        ) {
            Text(
                text = "Dismiss"
            )
        }
    })
}