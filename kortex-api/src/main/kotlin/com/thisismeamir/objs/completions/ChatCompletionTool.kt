package com.thisismeamir.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionTool(
    val function: ChatCompletionFunction,
    val type: String
)