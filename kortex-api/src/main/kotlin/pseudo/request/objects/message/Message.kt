package pseudo.request.objects.message

/**
 * Array of chat messages to be used for generating the chat completion.
 * Depending on the model you use, different message types (modalities) are supported,
 * like text, images, and audio. Currently, cortex only support text modalities.
 *
 *
 */
interface Message {
    val role: MessageRole
    val content: MessageContent
    val name: String
}