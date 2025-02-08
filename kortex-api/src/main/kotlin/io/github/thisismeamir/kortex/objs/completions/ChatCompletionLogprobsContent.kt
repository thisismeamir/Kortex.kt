package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionLogprobsContent(
    val bytes: Long,
    val logprob: Long,
    val token: String
)