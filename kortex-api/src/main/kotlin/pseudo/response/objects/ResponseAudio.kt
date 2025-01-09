package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class ResponseAudio(
    val data: String,
    val expiresAt: Long,
    val id: String,
    val transcript: String
)