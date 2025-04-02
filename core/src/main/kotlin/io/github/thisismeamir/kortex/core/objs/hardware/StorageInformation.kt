package io.github.thisismeamir.kortex.core.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class StorageInformation(
    val available:Long,
    val total: Long,
    val type:String
)
