package com.example.farmerpro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var loggedIn: Boolean = viewModel.isUserLoggedIn()
            if (loggedIn) {
                Toast.makeText(LocalContext.current, viewModel.email, Toast.LENGTH_LONG).show()
            }
            Navigation(loggedIn)
        }
    }
}
