package io.github.thisismeamir.kortex.core.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponseMessageToolCallFunction(
    val arguments: String,
    val name: String
)