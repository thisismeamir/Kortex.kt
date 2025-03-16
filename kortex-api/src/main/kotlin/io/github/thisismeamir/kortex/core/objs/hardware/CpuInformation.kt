package io.github.thisismeamir.kortex.core.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class CpuInformation(
    val arch: String,
    val cores: Int,
    val instructions: List<String>,
    val model: String
)