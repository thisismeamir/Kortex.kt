package objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionLogprobsRefusal(
    val bytes: Long,
    val logprob: Long,
    val token: String
)