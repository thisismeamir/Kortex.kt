package responseObjects

import kotlinx.serialization.Serializable
import pseudo.response.objects.Choice
import pseudo.response.objects.Usage

@Serializable
/**
 * Represents the response from the Chat Completion API
 * @param choices List of choices
 * @param created Unix timestamp of when the completion was created
 * @param id The completion ID
 * @param model The model used for the completion
 * @param `object` The object used for the completion
 * @param serviceTier The service tier used for the completion
 * @param systemFingerprint The system fingerprint
 * @param usage The token usage statistics for the entire request
 * @see Choice
 * @see ChatCompletionRequest
 */
data class ChatCompletionResponse(
    val choices: List<Choice>,
    val created: Long,
    val id: String,
    val model: String,
    val `object`: String,
    val serviceTier: String?,
    val systemFingerprint: String,
    val usage: Usage
)

