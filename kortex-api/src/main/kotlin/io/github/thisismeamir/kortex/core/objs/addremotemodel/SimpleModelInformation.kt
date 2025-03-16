package io.github.thisismeamir.kortex.core.objs.addremotemodel

import kotlinx.serialization.Serializable

@Serializable
data class SimpleModelInformation (
    val engine: String,
    val model: String,
    val version: String
)