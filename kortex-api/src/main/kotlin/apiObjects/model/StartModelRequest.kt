package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to start a model.
 * @param cache_type The KV cache type (optional, default is "f16").
 * @param caching_enabled To enable prompt caching or not (optional, default is false).
 * @param ctx_len The context length for model operations (optional).
 * @param mmproj Local path to mmproj model file (optional).
 * @param model The downloaded model name (required).
 * @param model_path Local path to LLM model file (optional).
 * @param n_parallel Number of parallel processing units to use (optional, default is 1).
 * @param ngl Determines GPU layer usage (optional).
 */
@Serializable
data class StartModelRequest(
    val cache_type: String? = "f16",
    val caching_enabled: Boolean? = false,
    val ctx_len: Int? = null,
    val mmproj: String? = null,
    val model: String,
    val model_path: String? = null,
    val n_parallel: Int? = 1,
    val ngl: Int? = null
)