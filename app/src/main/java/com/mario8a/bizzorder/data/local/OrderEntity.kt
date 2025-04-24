package com.mario8a.bizzorder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: String,
    val customerName: String,
    val item: String,
    val total: Double,
    val imageUrl: String
)