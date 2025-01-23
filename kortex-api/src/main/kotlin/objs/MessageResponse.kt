package objs

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val message: String?,
    val error: String?
)
