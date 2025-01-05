package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to stop a model.
 * @param model The downloaded model name.
 */
@Serializable
data class StopModelRequest(
    val model: String
)