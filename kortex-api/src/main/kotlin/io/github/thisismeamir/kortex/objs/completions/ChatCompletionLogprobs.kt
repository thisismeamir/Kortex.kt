package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionLogprobs(
    val content: List<io.github.thisismeamir.kortex.objs.completions.ChatCompletionLogprobsContent>,
    val refusal: List<io.github.thisismeamir.kortex.objs.completions.ChatCompletionLogprobsRefusal>,
    val topLogprobs: List<ChatCompletionLogprobsTopLogprobs>
)