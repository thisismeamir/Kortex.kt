package io.github.thisismeamir.kortex.core.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponseMessageToolCall(
    val function: ChatCompletionResponseMessageToolCallFunction,
    val id: String,
    val type: String
)