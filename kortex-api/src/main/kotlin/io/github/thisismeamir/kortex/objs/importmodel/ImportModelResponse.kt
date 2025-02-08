package io.github.thisismeamir.kortex.objs.importmodel

import kotlinx.serialization.Serializable

@Serializable
data class ImportModelResponse(
    val message: String,
    val model: String,
    val result: String
)