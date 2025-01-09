package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class Usage(
    val completionTokens: Int,
    val completionTokensDetails: TokenDetails,
    val promptTokens: Int,
    val promptTokensDetails: TokenDetails,
    val totalTokens: Int
)