package com.example.farmerpro.ui.home.fridge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens
import com.example.farmerpro.ui.landing.UserSignInViewModel

@Preview
@Composable
fun CommunityFridgeScreen(
    navController: NavController,
    viewModel: CommunityFridgeViewModel = hiltViewModel()
){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Community Fridge",
            modifier = Modifier.padding(bottom = 8.dp, top = 12.dp,
                start = 8.dp, end=4.dp), style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick =
            {
                viewModel.signOut()
                navController.navigate(Screens.Start.name)
            },
            shape = CircleShape,
            border= BorderStroke(1.dp, androidx.compose.ui.graphics.Color.Blue)
        ) {
            Text(
                    text = "Sort by: distance",
                    modifier = Modifier.padding(), style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
            )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
                name = "Community Fridge KW",
                address = "200 University Ave",
                distance = "0.1 km away",
                image = R.drawable.cf1
        )
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
                name = "For-All Community",
                address = "75 King St S Unit 56",
                distance = "2.1 km away",
                image = R.drawable.cf2
        )
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
                name = "Up Town Fridge",
                address = "200 King Street W",
                distance = "1.4 km away",
                image = R.drawable.cf3
        )
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
                name = "Plant-based CF",
                address = "100 Water St N",
                distance = "8.7 km away",
                image = R.drawable.cf4
        )
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
                name = "Guelph Fridge",
                address = "68 Wyndham St N",
                distance = "12.4 km away",
                image = R.drawable.cf5
        )
    }
}


