package com.thisismeamir.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class PowerInformation(
    val batteryLife: Int,
    val chargingStatus: String,
    val isPowerSaving: Boolean
)