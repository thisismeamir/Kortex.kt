package io.github.thisismeamir.kortex.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class HardwareInformation(
    val cpu: CpuInformation,
    val gpus: List<GpuInformation>,
    val os: OsInformation,
    val power: PowerInformation,
    val ram: RamInformation,
    val storage: StorageInformation
)