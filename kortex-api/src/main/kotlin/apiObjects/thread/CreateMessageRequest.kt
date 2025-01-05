package apiObjects.thread

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to create a message in a thread.
 * @param content The content of the message.
 * @param role The role of the message sender (e.g., "user", "assistant").
 */
@Serializable
data class CreateMessageRequest(
    val content: String,
    val role: String
)