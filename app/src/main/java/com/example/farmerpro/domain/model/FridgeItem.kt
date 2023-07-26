package com.example.farmerpro.domain.model

data class FridgeItem(
    var id: String? = null,
    var uid: String = "",
    var fridge_name: String = "",
    var location: String? = null,
    var fridgeinventory: String? = null,
    var contact_number: String? = null,
    var imageUrl: String = ""
)
