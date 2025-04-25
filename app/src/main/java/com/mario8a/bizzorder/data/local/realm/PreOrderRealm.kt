package com.mario8a.bizzorder.data.local.realm

import io.realm.kotlin.Realm
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Comparatira al DAO
class PreOrderRealm @Inject constructor(
    private val realm: Realm
) {
    suspend fun insertPreOrder(preOrdrObject: PreOrderObject) {
        realm.write {
            copyToRealm(preOrdrObject)
        }
    }

    fun getPreOrders(): Flow<List<PreOrderObject>> {
        return realm.query(PreOrderObject::class).asFlow().map {
            it.list
        }
    }

    suspend fun deletePreOrder(id: Long) {
        realm.write {
            query(PreOrderObject::class, "id == $0", id).first().find()?.let {
                delete(it)
            }
        }
    }

    suspend fun updateIsSent(id: Long, isSent: Boolean) {
        realm.write {
            query(PreOrderObject::class, "id == $0", id).first().find()?.let {
                it.isSent = isSent
            }
        }
    }


}