package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing metadata for adding a remote model.
 * @param api_key_url The URL for the API key.
 * @param author The author of the model.
 * @param description The description of the model.
 * @param end_point The endpoint for the model.
 * @param logo The logo of the model.
 */
@Serializable
data class AddRemoteModelMetadata(
    val api_key_url: String,
    val author: String,
    val description: String,
    val end_point: String,
    val logo: String
)