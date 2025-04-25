package com.mario8a.bizzorder.data.local.realm

import io.realm.kotlin.Realm
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Comparativa a DAO
class OrderRealm @Inject constructor(
    private val realm: Realm
) {

    suspend fun insertOrder(ordersEntity: List<OrderObject>) {
        realm.write {
            ordersEntity.forEach { orderEntity ->
                copyToRealm(orderEntity)
            }
        }
    }

    fun getOrders(): Flow<List<OrderObject>> {
        return realm.asFlow().map { realmChange ->
            realmChange.realm.query(OrderObject::class).find()
        }
    }

    suspend fun getOrder(id: String): OrderObject? {
        return realm.query(OrderObject::class, "id == $0", id).first().find()
    }

}