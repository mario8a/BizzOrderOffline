package com.mario8a.bizzorder.data.remote

import com.mario8a.bizzorder.domain.Order

fun OrderDto.toDomain(): Order {
    return Order(
        id = id,
        customerName = customerName,
        item = item,
        total = total,
        imageUrl = imageUrl
    )
}