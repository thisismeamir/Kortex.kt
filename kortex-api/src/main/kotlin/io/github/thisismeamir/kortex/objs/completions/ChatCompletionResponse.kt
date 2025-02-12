package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponse (
    val choices: List<io.github.thisismeamir.kortex.objs.completions.ChatCompletionChoice>,
    val created: Long,
    val id: String,
    val model: String,
    val `object`: String,
    val serviceTier: Boolean? =null,
    val systemFingerprint: String,
    val usage: io.github.thisismeamir.kortex.objs.completions.ChatCompletionUsage
)

