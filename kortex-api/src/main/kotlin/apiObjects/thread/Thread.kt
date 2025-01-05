package apiObjects.thread

import kotlinx.serialization.Serializable

/**
 * Data class representing a thread.
 * @param id The unique identifier of the thread.
 * @param metadata The metadata associated with the thread (optional).
 */
@Serializable
data class Thread(
    val id: String,
    val metadata: Map<String, String>? = null
)