package com.thisismeamir.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionUsage(
    val completionTokens: Long,
    val completionTokensDetails: ChatCompletionTokensDetails,
    val promptTokens: Long,
    val promptTokensDetails: ChatCompletionTokensDetails,
    val totalTokens: Long
)