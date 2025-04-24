package com.mario8a.bizzorder.data

import jakarta.inject.Inject

class RemoteDataStorage @Inject constructor(
    val bizOrderApi: BizOrderApi
) {
    // Estos dos metodos se comunicaran con el api externa
    suspend fun getOrders() = runCatching {
        val response = bizOrderApi.getOrders()
        if (response.isSuccessful) {
            response.body().orEmpty()
        } else {
            throw Exception("${response.code()}: ${response.message()}")
        }
    }

    suspend fun savePreOrders() = runCatching {
        val response = bizOrderApi.savePreOrders()
        if (response.isSuccessful) {
            response.body() ?: Unit
        } else {
            throw Exception("${response.code()}: ${response.message()}")
        }
    }
}