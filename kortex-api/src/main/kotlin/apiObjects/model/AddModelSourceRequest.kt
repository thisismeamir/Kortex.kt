package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to add a model source.
 * @param source The URL of the model source to add.
 */
@Serializable
data class AddModelSourceRequest(
    val source: String
)