package com.example.farmerpro.ui.landing

import PasswordInput
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens
import com.example.farmerpro.types.User
import com.example.farmerpro.components.BorderedButton
import com.example.farmerpro.components.TextInput
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun UserSignupScreen(
    navController: NavController, userType: String, viewModel: UserSignUpViewModel = hiltViewModel()
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signUpState.collectAsState(initial = null)
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
            value = fullName, onValueChange = { fullName = it }, placeholder = "Full Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextInput(
            value = email, onValueChange = { email = it }, placeholder = "Email"
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordInput(
            value = password, onValueChange = { password = it }, placeholder = "Password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextInput(
            value = contactNumber,
            onValueChange = {
                if (it.matches("[0-9]+".toRegex()) && it.length <= 10) {
                    contactNumber = it
                }
            },
            placeholder = "Contact Number",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator()
            }
        }
        BorderedButton(value = "Sign up", onClick = {
            if(contactNumber.length !== 10){
                FancyToast.makeText(context,"Please add valid phone number",FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show()
            } else if(fullName.isEmpty() || !fullName.matches(Regex("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*\$")))
            {
                FancyToast.makeText(context,"Please add valid full name",FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show()
            }
            else {
                scope.launch {
                    viewModel.signUpUser(User(fullName, userType, contactNumber), email, password)
                }
            }
        })
        Spacer(modifier = Modifier.height(16.dp))

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