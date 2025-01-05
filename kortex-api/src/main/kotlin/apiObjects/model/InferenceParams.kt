package apiObjects.model

import kotlinx.serialization.Serializable

/**
 * Data class representing inference parameters.
 * @param frequency_penalty The penalty for frequency.
 * @param max_tokens The maximum number of tokens.
 * @param presence_penalty The penalty for presence.
 * @param stream Whether to stream the output.
 * @param temperature The temperature for randomness.
 * @param top_p The top-p value for nucleus sampling.
 */
@Serializable
data class InferenceParams(
    val frequency_penalty: Double,
    val max_tokens: Int,
    val presence_penalty: Double,
    val stream: Boolean,
    val temperature: Double,
    val top_p: Double
)