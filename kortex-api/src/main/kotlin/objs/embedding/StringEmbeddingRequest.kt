package objs.embedding

import kotlinx.serialization.Serializable

@Serializable
data class StringEmbeddingRequest(
    override val embeddingFormat: String,
    override val input: String,
    override val `object`: String
) : CreateEmbeddingRequest