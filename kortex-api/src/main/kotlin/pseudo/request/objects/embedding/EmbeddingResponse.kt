package pseudo.request.objects.embedding

import kotlinx.serialization.Serializable


@Serializable
data class EmbeddingResponse (
    val embedding: List<Double>,
    val index: Int,
    val `object`: String
)
