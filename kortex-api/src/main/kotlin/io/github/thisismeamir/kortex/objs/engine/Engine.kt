package io.github.thisismeamir.kortex.objs.engine

import kotlinx.serialization.Serializable

@Serializable
data class Engine(
    val engine: String,
    val name: String,
    val version: String,
)

