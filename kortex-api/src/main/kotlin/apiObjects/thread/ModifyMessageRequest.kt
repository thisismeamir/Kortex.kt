package apiObjects.thread

import kotlinx.serialization.Serializable


/**
 * Data class representing a request to modify a message.
 * @param content The new content for the message.
 * @param metadata The updated metadata for the message.
 */
@Serializable
data class ModifyMessageRequest(
    val content: String ,
    val metadata: Map<String, String>
)