package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionChoice(
    val finishReason: String,
    val index: Int,
    val logprobs: ChatCompletionLogprobs? = null,
    val message: ChatCompletionResponseMessage
)