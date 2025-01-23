package objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponse (
    val choices: List<ChatCompletionChoice>,
    val created: Long,
    val id: String,
    val model: String,
    val `object`: String,
    val serviceTier: Boolean,
    val systemFingerprint: String,
    val usage: ChatCompletionUsage
)

