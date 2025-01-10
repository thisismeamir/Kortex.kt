package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val finish_reason: String,
    val index: Int,
    val logprobs: Logprobs,
    val message: ResponseMessage
)