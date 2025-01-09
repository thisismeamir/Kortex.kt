package pseudoObjects.message.usermessage

import kotlinx.serialization.Serializable
import pseudoObjects.message.Message
import pseudoObjects.message.MessageRole


@Serializable
data class UserModeMessage(
    override val name: String,
    override val content: UserModeMessageContent
) : Message {
    override val role: MessageRole = MessageRole.USER
}
