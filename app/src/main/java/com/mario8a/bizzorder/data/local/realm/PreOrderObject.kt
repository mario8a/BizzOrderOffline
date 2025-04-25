package com.mario8a.bizzorder.data.local.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class PreOrderObject: RealmObject {
    @PrimaryKey
    var id: Long = 0L
    var customerName: String = ""
    var item: String = ""
    var isSent: Boolean = false
}