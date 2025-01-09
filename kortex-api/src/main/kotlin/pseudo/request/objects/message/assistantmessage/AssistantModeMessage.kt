package pseudo.request.objects.message.assistantmessage

import kotlinx.serialization.Serializable
import pseudo.request.objects.message.Message
import pseudo.request.objects.message.MessageRole


@Serializable
data class AssistantModeMessage(
    val audio: AssistantModeAudio,
    override val content: AssistantModeContent,
    override val name: String,
    val refusal: String?,
    val toolCalls: Triple<String, String, Map<String, List<String>>> // id, type, name to arguments.
) : Message {
    override val role: MessageRole
        get() = MessageRole.ASSISTANT
}


