package io.github.thisismeamir.kortex.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class OsInformation(
    val name: String,
    val version: String
)