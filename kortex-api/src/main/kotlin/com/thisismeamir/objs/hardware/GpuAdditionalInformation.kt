package com.thisismeamir.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class GpuAdditionalInformation(
    val computeCap: String,
    val driverVersion: String
)