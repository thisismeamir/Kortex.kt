package objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageResponse(
    val completedAt: Long,
    val content: List<MessageContent>,
    val createdAt: Long,
    val id: String,
    val metadata: String,
    val `object`: String,
    val role: String,
    val status: String,
    val threadId: String,
)