package com.example.farmerpro.ui.home.fridge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.farmerpro.R
import com.example.farmerpro.domain.model.FridgeRequest
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.ui.theme.Gray400

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RequestCard(
    request: FridgeRequest,
    onCardClick: (FridgeRequest) -> Unit = {},
    ) {
    Card(
        onClick={onCardClick(request)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                BorderStroke(0.5.dp, Gray400), RoundedCornerShape(16.dp)
            )
    ) {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = request.fridge_name,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Start),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
            )
            Text(
                text = "${request.amount}lb " + request.product_name,
                style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Start),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp)
            )

            Text(
                text = request.location,
                style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Start, color = Color.DarkGray),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}