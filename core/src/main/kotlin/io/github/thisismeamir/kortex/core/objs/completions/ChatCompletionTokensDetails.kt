package io.github.thisismeamir.kortex.core.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionTokensDetails(
    val audioTokens: Long,
    val reasoningTokens: Long,
    val cachedTokens: Long
)