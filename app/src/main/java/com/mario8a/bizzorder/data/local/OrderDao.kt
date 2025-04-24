package com.mario8a.bizzorder.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    // Si el registri ya existe lo sustituye completo
    @Upsert
    suspend fun insertOrder(orderEntity: List<OrderEntity>)

    @Query("SELECT * FROM orders")
    fun getOrders(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrder(id: String): OrderEntity
}