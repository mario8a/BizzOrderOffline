package com.mario8a.bizzorder.data

import com.mario8a.bizzorder.data.local.room.OrderEntity
import com.mario8a.bizzorder.domain.Order
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