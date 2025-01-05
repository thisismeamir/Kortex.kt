package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to remove a model source.
 * @param source The URL of the model source to remove.
 */
@Serializable
data class RemoveModelSourceRequest(
    val source: String
)