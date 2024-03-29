package com.example.farmerpro.ui.home.markets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.farmerpro.R
import com.example.farmerpro.components.ExpandableText
import com.example.farmerpro.components.LocationText
import com.example.farmerpro.components.PhoneNumberText
import com.example.farmerpro.components.StaticRatingBar
import com.example.farmerpro.domain.model.MarketplaceItem
import kotlin.math.round
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ItemDialog(
    closeDialog: () -> Unit,
    item: MarketplaceItem,
    owner: Boolean,
    deleteItem: () -> Unit,
    ratingDialog: () -> Unit,
    ) {
    Dialog(onDismissRequest = closeDialog) {
        Surface(shape = RoundedCornerShape(20.dp), elevation = 24.dp) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                if (item.imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(item.imageUrl)
                            .crossfade(true).build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .align(CenterHorizontally)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.default_fruits),
                        contentDescription = "Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .align(CenterHorizontally)
                            .padding(8.dp)
                    )
                }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = item.product_name,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    )

                if(item.rating != null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = roundToOneDecimalPlace(item.rating!!).toString(),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black
                                ),
                                color = Color(0xFF8d8d8d),
                            )
                            StaticRatingBar(rating = item.rating!!.roundToInt())
                            Text(
                                text = "(" + item.numberOfRatings + " ratings)",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Start,
                                    color = Color.Black
                                ),
                                color = Color(0xFF8d8d8d),
                            )
                        }
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { ratingDialog() },
                                modifier = Modifier.size(32.dp)
                                    .background(
                                        MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(0.dp)
                                    )
                            ) {
                                Icon(
                                    Icons.Filled.Add,
                                    contentDescription = "Plus Icon",
                                    tint = Color.White,
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sold by: ",
                        maxLines = 1,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = item.seller,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        ),
                        maxLines = 1,
                        color = Color(0xFF8d8d8d),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Price: ",
                        maxLines = 1,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "$${item.price.orEmpty()} CAD/lb",
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
                        maxLines = 1,
                    )
                }
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
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Contact Number: ",
                        maxLines = 1,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    )
                    PhoneNumberText(item.contact_number)
                }
                if(item.description != null && item.description!!.isNotEmpty()) {
                    ExpandableText(
                        text = item.description.orEmpty(),
                    )
                }
                if(owner) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                                deleteItem()
                                closeDialog()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFA80000)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Delete Posting",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                    Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

fun roundToOneDecimalPlace(number: Double): Double {
    val factor = 10.0
    return round(number * factor) / factor
}
