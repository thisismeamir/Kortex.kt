package objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageRequestBody(
    val content: String,
    val role: String
)