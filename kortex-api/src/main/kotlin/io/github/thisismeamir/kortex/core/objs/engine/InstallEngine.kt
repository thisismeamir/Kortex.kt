package io.github.thisismeamir.kortex.core.objs.engine

import kotlinx.serialization.Serializable

@Serializable
data class InstallEngine(
    val apiKey: String,
    val metadata: Map<String,String>,
    val type: String,
    val url: String,
    val variant: String,
    val version: String
)