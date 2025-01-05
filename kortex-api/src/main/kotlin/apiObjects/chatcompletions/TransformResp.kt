package apiObjects.chatcompletions

import apiObjects.embedding.EmbeddingRequest
import kotlinx.serialization.Serializable

/**
 * Data class representing a response for transformations.
 * @param chatCompletions The chat completions configuration.
 * @param embeddings The embeddings configuration.
 */
@Serializable
data class TransformResp(
    val chatCompletions: ChatCompletions,
    val embeddings: List<EmbeddingRequest>
)