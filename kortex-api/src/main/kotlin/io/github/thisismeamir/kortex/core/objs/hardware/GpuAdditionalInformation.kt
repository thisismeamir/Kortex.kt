package io.github.thisismeamir.kortex.core.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class GpuAdditionalInformation(
    val computeCap: String,
    val driverVersion: String
)