package io.github.thisismeamir.kortex.core.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponseMessage(
    val audio: ChatCompletionResponseMessageAudio? = null,
    val content: String,
    val functionCall: ChatCompletionResponseMessageFunctionCall? = null,
    val refusal: String? = null,
    val role: String,
    val toolCalls: List<ChatCompletionResponseMessageToolCall>? =null
)