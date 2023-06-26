package com.example.farmerpro.ui.home.fridge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.R

@Preview
@Composable
fun fridge(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(4.dp),
        ){

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Image",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        )

        Column() {
            Text(
                text = "Community Fridge 1",
                modifier = Modifier.padding(), style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start
                )
            )

            Text(
                text = "172 Cousens Terrace",
                modifier = Modifier.padding(), style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
            )

            Text(
                text = "1.4 km away",
                modifier = Modifier.padding(), style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
            )
        }
    }
}
