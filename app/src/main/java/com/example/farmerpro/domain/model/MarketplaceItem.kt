package com.example.farmerpro.domain.model

import com.example.farmerpro.R

data class MarketplaceItem(
    var id: String? = null,
    var uid: String = "",
    var product_name: String = "",
    var seller: String? = null,
    var price: String? = null,
    var location: String? = null,
    var description: String? = null,
    var contact_number: String? = null,
    var imageUrl: String = ""
)