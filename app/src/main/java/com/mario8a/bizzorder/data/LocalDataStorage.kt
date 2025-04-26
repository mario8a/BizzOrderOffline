package com.mario8a.bizzorder.data

import com.mario8a.bizzorder.data.local.realm.OrderObject
import com.mario8a.bizzorder.data.local.realm.OrderRealm
import com.mario8a.bizzorder.data.local.realm.PreOrderObject
import com.mario8a.bizzorder.data.local.realm.PreOrderRealm
import com.mario8a.bizzorder.data.local.room.OrderDao
import com.mario8a.bizzorder.data.local.room.OrderEntity
import com.mario8a.bizzorder.data.local.room.PreOrderDao
import com.mario8a.bizzorder.data.local.room.PreOrderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataStorage @Inject constructor(
    private val orderDao: OrderDao, // Room
    private val preOrderDao: PreOrderDao, // Room
    private val orderRealm: OrderRealm, // Realm
    private val preOrderRealm: PreOrderRealm // Realm
) {
    //ORDERS
    // Order ROOM
    suspend fun upsertOrderRoom(orders: List<OrderEntity>) = orderDao.insertOrder(orders)

    fun getOrdersRoom() = orderDao.getOrders()

    suspend fun getOrderByOrderRoom(id: String) = runCatching {
        orderDao.getOrder(id)
    }

    // Orders REALM
    suspend fun insertOrderRealm(orders: List<OrderObject>) = orderRealm.insertOrder(orders)

    fun getOrdersRealm() = orderRealm.getOrders()

    suspend fun insertOrderRealm(id: String): Result<OrderObject?> = runCatching {
        orderRealm.getOrder(id)
    }

    // PREORDERS
    // Preorder REALM
    suspend fun savePreOrderRealm(preOrder: PreOrderObject) = preOrderRealm.insertPreOrder(preOrder)
    fun getPreOrderRealm():Flow<List<PreOrderObject>> = preOrderRealm.getPreOrders()
    suspend fun deleteByPreOrderIdRealm(id: Long) = preOrderRealm.deletePreOrder(id)
    suspend fun retrySyncRealm(id: Long, isSent: Boolean) = preOrderRealm.updateIsSent(id, isSent)

    // PreOrder ROOM

    suspend fun savePreOrderRoom(preOrder: PreOrderEntity) = preOrderDao.insertPreOrder(preOrder)
    fun getPreOrderRoom():Flow<List<PreOrderEntity>> = preOrderDao.getPreOrders()
    suspend fun deleteByPreOrderIdRoom(id: String ) = preOrderDao.deletePreOrder(id)
    suspend fun retrySyncRoom(id: Long, isSent: Boolean) = preOrderDao.updatePreOrder(id, isSent)
}