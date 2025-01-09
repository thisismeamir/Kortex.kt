package pseudo.request.objects.message.systemmessage

import kotlinx.serialization.Serializable
import pseudo.request.objects.message.Message
import pseudo.request.objects.message.MessageRole


@Serializable
data class SystemModeMessage(
    override val name: String,
    override val content: SystemlModeMessageContent
) : Message {
    override val role: MessageRole = MessageRole.SYSTEM
}
