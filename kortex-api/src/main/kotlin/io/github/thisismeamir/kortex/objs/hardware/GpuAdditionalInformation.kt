package io.github.thisismeamir.kortex.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class GpuAdditionalInformation(
    val computeCap: String,
    val driverVersion: String
)