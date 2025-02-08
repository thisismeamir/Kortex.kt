package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponseMessageToolCall(
    val function: io.github.thisismeamir.kortex.objs.completions.ChatCompletionResponseMessageToolCallFunction,
    val id: String,
    val type: String
)