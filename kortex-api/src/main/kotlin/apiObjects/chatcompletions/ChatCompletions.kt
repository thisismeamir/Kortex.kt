package apiObjects.chatcompletions

import kotlinx.serialization.Serializable

/**
 * Data class representing chat completions configuration.
 * @param template The template for chat completions.
 * @param url The URL for chat completions.
 */
@Serializable
data class ChatCompletions(
    val template: String,
    val url: String
)