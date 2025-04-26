package com.mario8a.bizzorder.domain

import kotlinx.coroutines.flow.Flow

interface PreOrderRepository {

    suspend fun savePreOrder(preOrder: PreOrder): Result<Unit>

    suspend fun getPreOrders(): Flow<Result<List<PreOrder>>>

    suspend fun deletePreOrder(id: Long)

    suspend fun retrySync(id: Long)
}