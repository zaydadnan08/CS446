package com.example.farmerpro.ui.landing

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)