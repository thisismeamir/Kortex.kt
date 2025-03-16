package io.github.thisismeamir.kortex.core.objs.engine

import kotlinx.serialization.Serializable

@Serializable
data class VariantRequestBody(
    val variant: String,
    val version: String
)