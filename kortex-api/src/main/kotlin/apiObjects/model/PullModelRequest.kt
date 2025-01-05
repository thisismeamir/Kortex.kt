package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to pull a model from a remote source.
 * @param id The id which will be used to register the model (optional).
 * @param model The identifier or URL of the model to use.
 * @param name The name which will be used to overwrite the model name (optional).
 */
@Serializable
data class PullModelRequest(
    val id: String? = null,
    val model: String,
    val name: String? = null
)