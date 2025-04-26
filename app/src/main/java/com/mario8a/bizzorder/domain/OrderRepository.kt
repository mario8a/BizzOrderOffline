package com.mario8a.bizzorder.domain

import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getOrder(): Flow<Result<List<Order>>>

    suspend fun getOrderById(id: String): Result<Order?>
}