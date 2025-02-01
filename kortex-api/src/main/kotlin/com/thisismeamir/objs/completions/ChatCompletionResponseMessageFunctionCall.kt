package com.thisismeamir.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponseMessageFunctionCall(
    val arguments: String,
    val name: String
)