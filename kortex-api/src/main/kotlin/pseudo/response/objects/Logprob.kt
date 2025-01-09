package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class Logprob(
    val bytes: String?,
    val logprob: Int,
    val token: String
)