package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val finishReason: String,
    val index: Int,
    val logprobs: Logprobs,
    val message: ResponseMessage
)