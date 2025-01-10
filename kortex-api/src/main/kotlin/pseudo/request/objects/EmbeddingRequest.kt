package pseudo.request.objects

import kotlinx.serialization.Serializable

@Serializable
data class EmbeddingRequest(val encodingFormat: String, val input: Double, val model: String)
