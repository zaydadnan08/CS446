package com.example.farmerpro.ui.landing

import PasswordInput
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens
import com.example.farmerpro.components.BorderedButton
import com.example.farmerpro.components.TextInput
import kotlinx.coroutines.launch

@Composable
fun UserSigninScreen(
    navController: NavController, viewModel: UserSignInViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.farmer),
            modifier = Modifier.size(64.dp),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(32.dp))

        TextInput(
            value = email, onValueChange = { email = it }, placeholder = "Email"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordInput(
            value = password, onValueChange = { password = it }, placeholder = "Password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator()
            }
        }

        BorderedButton(value = "Sign in", onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)
                }
        })

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            onClick = {
                scope.launch {
                    navController.navigate(Screens.UserSelect.name);
                }
            },
        ) {
            Text(text = "Don't have an account? Create one", color = Color.Black)
        }

        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    val success = state.value?.isSuccess
                    Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
                    navController.navigate(Screens.Home.name);
                }
            }
        }

        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}