package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to update a model instance.
 * @param api_key_template The template for the API key (optional).
 * @param ctx_len The context length for the model (optional).
 * @param dynatemp_exponent The exponent for dynamic temperature adjustment (optional).
 * @param dynatemp_range The range for dynamic temperature adjustment (optional).
 * @param engine The engine used to run the model (optional).
 * @param files The list of file paths associated with the model (optional).
 * @param frequency_penalty The penalty for frequency (optional).
 * @param ignore_eos Whether to ignore end-of-sequence tokens during generation (optional).
 * @param inference_params The inference parameters (optional).
 * @param max_tokens The maximum number of tokens (optional).
 * @param min_keep The minimum number of tokens to keep in the buffer (optional).
 * @param min_p The minimum probability threshold for token selection (optional).
 * @param mirostat Whether to enable Mirostat sampling (optional).
 * @param mirostat_eta The learning rate for Mirostat sampling (optional).
 * @param mirostat_tau The target entropy for Mirostat sampling (optional).
 * @param n_parallel The number of parallel threads for execution (optional).
 * @param n_probs The number of probabilities to consider for each token (optional).
 * @param ngl The number of GPU layers (optional).
 * @param penalize_nl Whether to penalize newline tokens (optional).
 * @param presence_penalty The penalty for presence (optional).
 * @param prompt_template The template used for formatting prompts (optional).
 * @param repeat_last_n The number of recent tokens to consider for repetition penalty (optional).
 * @param repeat_penalty The penalty applied to repeated tokens (optional).
 * @param seed The seed for random number generation to ensure reproducibility (optional).
 * @param stop The tokens that signal the end of generation (optional).
 * @param stream Whether to stream the output as it is generated (optional).
 * @param temperature The temperature for randomness (optional).
 * @param tfs_z The threshold for token frequency sampling (optional).
 * @param top_k The limit for the sampling pool to the top_k most probable tokens (optional).
 * @param top_p The top-p value for nucleus sampling (optional).
 * @param typ_p The typical-p value for sampling (optional).
 * @param version The version of the model (optional).
 */
@Serializable
data class UpdateModelRequest(
    val api_key_template: String? = null,
    val ctx_len: Int? = null,
    val dynatemp_exponent: Double? = null,
    val dynatemp_range: Double? = null,
    val engine: String? = null,
    val files: List<String>? = null,
    val frequency_penalty: Double? = null,
    val ignore_eos: Boolean? = null,
    val inference_params: InferenceParams? = null,
    val max_tokens: Int? = null,
    val min_keep: Int? = null,
    val min_p: Double? = null,
    val mirostat: Boolean? = null,
    val mirostat_eta: Double? = null,
    val mirostat_tau: Double? = null,
    val n_parallel: Int? = null,
    val n_probs: Int? = null,
    val ngl: Int? = null,
    val penalize_nl: Boolean? = null,
    val presence_penalty: Double? = null,
    val prompt_template: String? = null,
    val repeat_last_n: Int? = null,
    val repeat_penalty: Double? = null,
    val seed: Int? = null,
    val stop: List<String>? = null,
    val stream: Boolean? = null,
    val temperature: Double? = null,
    val tfs_z: Double? = null,
    val top_k: Int? = null,
    val top_p: Double? = null,
    val typ_p: Double? = null,
    val version: String? = null
)