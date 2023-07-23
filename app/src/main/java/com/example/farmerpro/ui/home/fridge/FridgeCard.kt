package com.example.farmerpro.ui.home.fridge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.farmerpro.R
import com.example.farmerpro.domain.model.FridgeItem
import com.example.farmerpro.domain.model.FridgeRequest
import com.example.farmerpro.ui.theme.Gray400

@Composable
fun FridgeCard(
    fridgeItem: FridgeItem,
    onCardClick: (FridgeRequest) -> Unit = {},
        ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .border(
                    BorderStroke(0.5.dp, Gray400),
                    RoundedCornerShape(12.dp)
                )
        )
        {
            if (fridgeItem.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(fridgeItem.imageUrl)
                        .crossfade(true).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.cf1),
                    contentDescription = "Image",
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp),
            ) {
                Text(
                    text = fridgeItem.fridge_name,
                    modifier = Modifier.padding(), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start
                    )
                )
                fridgeItem.location?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(), style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                }
            }
        }
}


