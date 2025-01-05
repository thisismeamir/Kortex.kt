package apiObjects.file

import kotlinx.serialization.Serializable

/**
 * Data class representing a file.
 * @param id The unique identifier of the file.
 * @param name The name of the file.
 * @param purpose The purpose of the file.
 */
@Serializable
data class File(
    val id: String,
    val name: String,
    val purpose: String
)