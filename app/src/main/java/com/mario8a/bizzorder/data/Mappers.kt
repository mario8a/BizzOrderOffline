package com.mario8a.bizzorder.data

import com.mario8a.bizzorder.data.local.realm.OrderObject
import com.mario8a.bizzorder.data.local.realm.PreOrderObject
import com.mario8a.bizzorder.data.local.room.OrderEntity
import com.mario8a.bizzorder.data.local.room.PreOrderEntity
import com.mario8a.bizzorder.domain.Order
import com.mario8a.bizzorder.domain.PreOrder

// Mapeo de una entidad de room a una de dominio
fun OrderEntity.toDomain(): Order {
    return Order(
        id = id,
        customerName, item, total, imageUrl
    )
}
// domain a entity
fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        customerName = customerName,
        item = item,
        total = total,
        imageUrl = imageUrl
    )
}
// Entidad de db room a domain
fun PreOrderEntity.toDomain(): PreOrder {
    return PreOrder(
        id = id,
        customerName = customerName,
        product = item,
        isSent = isSent
    )
}

// Realm
// Realm a domain
fun OrderObject.toDomain(): Order {
    return Order(
        id = id,
        customerName, item, total, imageUrl
    )
}
// Entidad de dominio a realm
fun Order.toRealm(): OrderObject {
    return OrderObject().apply {
        id = this@toRealm.id
        customerName = this@toRealm.customerName
        item = this@toRealm.item
        total = this@toRealm.total
        imageUrl =this@toRealm.imageUrl
    }
}

fun PreOrderObject.toDomain(): PreOrder {
    return PreOrder(
        id = id,
        customerName = customerName,
        product = item,
        isSent = isSent
    )
}