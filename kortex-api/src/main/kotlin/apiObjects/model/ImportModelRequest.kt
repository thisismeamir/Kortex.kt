package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to import a model.
 * @param model The unique identifier of the model.
 * @param modelPath The file path to the model.
 * @param name The display name of the model (optional).
 * @param option The import option such as symlink or copy (optional).
 */
@Serializable
data class ImportModelRequest(
    val model: String,
    val modelPath: String,
    val name: String? = null,
    val option: String? = null
)