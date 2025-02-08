package io.github.thisismeamir.kortex.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class HardwareInformation(
    val cpu: io.github.thisismeamir.kortex.objs.hardware.CpuInformation,
    val gpus: List<io.github.thisismeamir.kortex.objs.hardware.GpuInformation>,
    val os: io.github.thisismeamir.kortex.objs.hardware.OsInformation,
    val power: io.github.thisismeamir.kortex.objs.hardware.PowerInformation,
    val ram: io.github.thisismeamir.kortex.objs.hardware.RamInformation,
    val storage: io.github.thisismeamir.kortex.objs.hardware.StorageInformation
)