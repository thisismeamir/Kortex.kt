package pseudo.request.objects.message

import kotlinx.serialization.Serializable



@Serializable
/**
 * Array of chat messages to be used for generating the chat completion.
 * Depending on the model you use, different message types (modalities) are supported,
 * like text, images, and audio. Currently, cortex only support text modalities.
 *
 *
 */
data class Message (
     val role: String = MessageRole.USER.name.lowercase(),
     val content: String,
     val name: String
    )