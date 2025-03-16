package io.github.thisismeamir.kortex.core.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionLogprobs(
    val content: List<ChatCompletionLogprobsContent>,
    val refusal: List<ChatCompletionLogprobsRefusal>,
    val topLogprobs: List<ChatCompletionLogprobsTopLogprobs>
)