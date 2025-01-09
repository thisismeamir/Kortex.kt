package pseudoObjects.message.toolmessage

import kotlinx.serialization.Serializable
import pseudoObjects.message.Message
import pseudoObjects.message.MessageRole


@Serializable
data class ToolModeMessage(
    override val content: ToolModeMessageContent,
    override val name: String,
    val toolID: String
) : Message {
    override val role: MessageRole
        get() = MessageRole.TOOL
}


