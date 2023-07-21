package com.example.farmerpro.domain.model


data class FridgeRequest(
    var product_name: String = "",
    var description: String = "",
    var amount: String = "",
    var location: String = "",
    var contact_number: String = "",
    var fridge_name: String = "",
    var uid: String = "",
)