package apiObjects.embedding

import kotlinx.serialization.Serializable

@Serializable
data class EmbeddingRequest(
    val encodingFormat: String = "float",
    val input: Double,
    val model: String
)

