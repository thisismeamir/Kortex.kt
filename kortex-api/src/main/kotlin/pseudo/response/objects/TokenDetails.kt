package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class TokenDetails(
    val audioTokens: Int,
    val cachedTokens: Int,
    val reasoningTokens: Int
)