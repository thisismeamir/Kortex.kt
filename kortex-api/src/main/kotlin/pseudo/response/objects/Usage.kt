package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class Usage(
    val completion_tokens: Int,
    val completion_tokens_details: TokenDetails,
    val prompt_tokens: Int,
    val prompt_tokens_details: TokenDetails,
    val total_tokens: Int
)