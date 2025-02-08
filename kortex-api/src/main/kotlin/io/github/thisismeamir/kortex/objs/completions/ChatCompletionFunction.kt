package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionFunction(
    val description: String,
    val name: String,
    val parameters: Map<String, String>,
    val strict: Boolean
)