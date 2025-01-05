package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to stop a model download.
 * @param taskId The unique identifier of the download task.
 */
@Serializable
data class StopModelDownloadRequest(
    val taskId: String
)