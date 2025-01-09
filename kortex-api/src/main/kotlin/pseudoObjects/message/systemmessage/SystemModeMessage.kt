package pseudoObjects.message.systemmessage

import kotlinx.serialization.Serializable
import pseudoObjects.message.Message
import pseudoObjects.message.MessageRole


@Serializable
data class SystemModeMessage(
    override val name: String,
    override val content: SystemlModeMessageContent
) : Message {
    override val role: MessageRole = MessageRole.SYSTEM
}
