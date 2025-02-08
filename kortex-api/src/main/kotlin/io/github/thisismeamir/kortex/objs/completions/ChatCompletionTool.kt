package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionTool(
    val function: io.github.thisismeamir.kortex.objs.completions.ChatCompletionFunction,
    val type: String
)