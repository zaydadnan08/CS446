package com.example.farmerpro.ui.home.markets.components

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.farmerpro.R
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.ui.home.markets.MarketViewModel

@ExperimentalComposeUiApi
@Composable
fun AddRatingDialog(
    closeDialog: () -> Unit,
    itemId: String,
    item: MarketplaceItem,
    viewModel: MarketViewModel
) {

    var ratingState by remember {
        mutableStateOf(5)
    }

    var selected by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (selected) 54.dp else 54.dp,
        spring(Spring.DampingRatioMediumBouncy)
    )

    AlertDialog(onDismissRequest = closeDialog, text = {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                    for (i in 1..5) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_star_24),
                            contentDescription = "star",
                            modifier = Modifier
                                .width(size)
                                .height(size)
                                .pointerInteropFilter {
                                    when (it.action) {
                                        MotionEvent.ACTION_DOWN -> {
                                            selected = true
                                            ratingState = i
                                        }

                                        MotionEvent.ACTION_UP -> {
                                            selected = false
                                        }
                                    }
                                    true
                                },
                            tint = if (i <= ratingState) Color(0xFFFFD700) else Color(0xFFA2ADB1)
                        )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }, confirmButton = {
        TextButton(
            onClick = {
                viewModel.updateItem(itemId, item, ratingState.toDouble())
                    closeDialog()
                    //addItem(name, price_per_lb, description, location)
            },
        ) {
            Text(
                text = "Add"
            )
        }
    }, dismissButton = {
        TextButton(
            onClick = closeDialog
        ) {
            Text(
                text = "Dismiss"
            )
        }
    })
}