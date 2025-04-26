package com.mario8a.bizzorder.domain

data class Order(
    val id: String,
    val customerName: String,
    val item: String,
    val total: Double,
    val imageUrl: String
)
