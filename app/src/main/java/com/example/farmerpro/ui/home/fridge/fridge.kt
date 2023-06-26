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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.R
import com.example.farmerpro.ui.theme.Gray400

@Preview
@Composable
fun fridge(
        name: String = "",
        address: String = "",
        distance: String = "",
        image: Int = 0
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

            Image(
                painter = painterResource(id = image),
                contentDescription = "Image",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Column(
                modifier = Modifier
                    .padding(8.dp),
            ) {
                Text(
                    text = name,
                    modifier = Modifier.padding(), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start
                    )
                )

                Text(
                    text = address,
                    modifier = Modifier.padding(), style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                )

                Text(
                    text = distance,
                    modifier = Modifier.padding(), style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                )
            }
        }
}


