package io.github.thisismeamir.kortex.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class GpuInformation(
    val activated: Boolean,
    val additionalInformation: io.github.thisismeamir.kortex.objs.hardware.GpuAdditionalInformation,
    val driverVersion: String,
    val freeVram: Long,
    val id: String,
    val name: String,
    val totalVram: Long,
    val uuid: String,
    val version: String
)