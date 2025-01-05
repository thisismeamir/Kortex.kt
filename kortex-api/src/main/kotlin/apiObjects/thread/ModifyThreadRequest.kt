package apiObjects.thread

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to modify a thread.
 * @param metadata The metadata to update in the thread.
 */
@Serializable
data class ModifyThreadRequest(
    val metadata: Map<String, String>
)