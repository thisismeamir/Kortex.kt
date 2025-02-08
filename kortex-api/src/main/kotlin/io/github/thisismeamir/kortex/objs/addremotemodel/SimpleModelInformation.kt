package io.github.thisismeamir.kortex.objs.addremotemodel

import kotlinx.serialization.Serializable

@Serializable
data class SimpleModelInformation (
    val engine: String,
    val model: String,
    val version: String
)