package io.github.thisismeamir.kortex.core.objs.importmodel

import kotlinx.serialization.Serializable

@Serializable
data class ImportModelRequest(
    val model: String,
    val modelPath: String,
    val name: String,
    val option: String
)

