package pseudo.request.objects.message.usermessage

import kotlinx.serialization.Serializable
import pseudo.request.objects.message.Message
import pseudo.request.objects.message.MessageRole


@Serializable
data class UserModeMessage(
    override val name: String,
    override val content: UserModeMessageContent
) : Message {
    override val role: MessageRole = MessageRole.USER
}
