package com.mario8a.bizzorder.data

import com.mario8a.bizzorder.data.remote.RemoteDataStorage
import com.mario8a.bizzorder.data.remote.toDomain
import com.mario8a.bizzorder.domain.Order
import com.mario8a.bizzorder.domain.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class OrderRepositoryImpl(
    private val remoteDataStorage: RemoteDataStorage,
    private val localDataStorage: LocalDataStorage
): OrderRepository {
    override fun getOrder(): Flow<Result<List<Order>>> {
        return localDataStorage.getOrdersRoom()
            .map { localOrders ->
                Result.success(localOrders.map { it.toDomain() })
            }.onStart {
                val remoteResult = remoteDataStorage.getOrders().mapCatching { list ->
                    list.map { it.toDomain() }
                }
                remoteResult.getOrNull()?.let { remoteOrders ->
                    localDataStorage.upsertOrderRoom(remoteOrders.map { it.toEntity() })
                }
            }.catch { exception ->
                emit(Result.failure(exception))
            }
    }

    override suspend fun getOrderById(id: String) = localDataStorage.getOrderByOrderRoom(id).mapCatching {
        it.toDomain()
    }
}