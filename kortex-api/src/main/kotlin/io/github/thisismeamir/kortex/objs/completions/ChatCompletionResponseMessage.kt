package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponseMessage(
    val audio: io.github.thisismeamir.kortex.objs.completions.ChatCompletionResponseMessageAudio,
    val content: String,
    val functionCall: ChatCompletionResponseMessageFunctionCall,
    val refusal: String,
    val role: String,
    val toolCalls: List<ChatCompletionResponseMessageToolCall>
)