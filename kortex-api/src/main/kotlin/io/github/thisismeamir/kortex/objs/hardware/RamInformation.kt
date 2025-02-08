package io.github.thisismeamir.kortex.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class  RamInformation(
    val available: Long,
    val total: Long,
    val type: String
)