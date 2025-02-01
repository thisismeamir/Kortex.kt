package com.thisismeamir.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class  RamInformation(
    val available: Long,
    val total: Long,
    val type: String
)