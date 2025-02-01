package com.thisismeamir.objs.engine

import kotlinx.serialization.Serializable

@Serializable
data class VariantRequestBody(
    val variant: String,
    val version: String
)