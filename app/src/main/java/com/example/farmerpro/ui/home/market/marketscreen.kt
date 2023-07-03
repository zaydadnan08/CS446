package com.example.farmerpro.ui.home.market

import Item
import MarketplaceItemCard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.R
import com.example.farmerpro.ui.theme.Gray200
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarketScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    ) {
        Text(
            text = "Marketplace",
            modifier = Modifier.padding(bottom = 8.dp, top = 12.dp, start = 8.dp, end = 4.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        val (searchQuery, setSearchQuery) = remember { mutableStateOf("") }
        SearchAppBar(searchQuery, setSearchQuery)
        Spacer(modifier = Modifier.height(8.dp))

        val items = listOf(
            Item("Apple", "$2.99/lb", "Sweet and crispy", R.drawable.apple),
            Item("Carrot", "$1.49/lb", "Crunchy and nutritious", R.drawable.carrot),
            Item("Blueberry", "$3.99/pint", "Juicy and antioxidant-rich", R.drawable.blueberry),
            Item("Banana", "$0.49/each", "Naturally sweet and energy-boosting", R.drawable.banana),
            Item("Onion", "$0.99/lb", "Adds flavor to various dishes", R.drawable.onion),
            Item("Potato", "$1.99/lb", "Versatile and filling", R.drawable.potato),
            Item("Strawberry", "$4.99/pint", "Sweet and refreshing", R.drawable.strawberry),
            Item("Orange", "$0.79/each", "Citrus fruit with tangy flavor", R.drawable.orange)
        )

        val filteredItems = if (searchQuery.isNotEmpty()) {
            items.filter { item ->
                item.name.contains(searchQuery, ignoreCase = true)
            }
        } else {
            items
        }

        LazyVerticalGrid(
            columns= GridCells.Fixed(2),
            modifier = Modifier.padding(1.dp)) {
            filteredItems.forEach { item ->
                item {
                    MarketplaceItemCard(item = item)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray200, RoundedCornerShape(24.dp))
            .border(
                BorderStroke(0.dp, Color.Transparent),
                RoundedCornerShape(24.dp)
            ),
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = "Search...",
                style = TextStyle(fontSize = 18.sp)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            cursorColor = Color.Gray
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        ),
    )
}

