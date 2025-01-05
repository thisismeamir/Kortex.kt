package apiObjects.chatcompletions

import apiObjects.embedding.EmbeddingRequest
import kotlinx.serialization.Serializable

/**
 * Data class representing a request for transformations.
 * @param chatCompletions The chat completions configuration.
 * @param embeddings The embeddings configuration.
 * @param getModels The get models configuration.
 */
@Serializable
data class TransformReq(
    val chatCompletions: ChatCompletions,
    val embeddings: List<EmbeddingRequest>,
    val getModels: Map<String, List<String>>
)