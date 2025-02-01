package com.thisismeamir.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class GpuInformation(
    val activated: Boolean,
    val additionalInformation: GpuAdditionalInformation,
    val driverVersion: String,
    val freeVram: Long,
    val id: String,
    val name: String,
    val totalVram: Long,
    val uuid: String,
    val version: String
)