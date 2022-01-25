package com.ccarrera.petshop.models

data class Product(
    var id: Int,
    var name: String,
    var animal: String,
    var productType: String,
    var details: String,
    var image: String,
    var price: Float
)
