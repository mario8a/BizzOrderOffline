package com.mario8a.bizzorder.data

import com.mario8a.bizzorder.data.local.realm.PreOrderObject
import com.mario8a.bizzorder.data.remote.RemoteDataStorage
import com.mario8a.bizzorder.domain.PreOrder
import com.mario8a.bizzorder.domain.PreOrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreOrderRepositoryImpl(
    private val remoteDataStorage: RemoteDataStorage,
    private val localDataStorage: LocalDataStorage
) : PreOrderRepository {
    override suspend fun savePreOrder(preOrder: PreOrder) =
        remoteDataStorage.savePreOrders().also { result ->
            localDataStorage.savePreOrderRealm(
//                PreOrderEntity(
//                    id = preOrder.id,
//                    customerName = preOrder.customerName,
//                    item = preOrder.product,
//                    isSent = result.isSuccess
//                )
                PreOrderObject().apply {
                    customerName = preOrder.customerName
                    item = preOrder.product
                    isSent = result.isSuccess
                }
            )
        }

    override fun getPreOrders(): Flow<Result<List<PreOrder>>> {
        return localDataStorage.getPreOrderRealm().map { preOrders ->
            runCatching {
                preOrders.map { it.toDomain() }
            }
        }
    }

    override suspend fun deletePreOrder(id: Long) = localDataStorage.deleteByPreOrderIdRealm(id)

    override suspend fun retrySync(id: Long) {
        val result = remoteDataStorage.savePreOrders()
        if (result.isSuccess) {
            localDataStorage.retrySyncRealm(id, true)
        }
    }

}