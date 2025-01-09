package pseudo.request.objects.message.toolmessage

import kotlinx.serialization.Serializable
import pseudo.request.objects.message.Message
import pseudo.request.objects.message.MessageRole


@Serializable
data class ToolModeMessage(
    override val content: ToolModeMessageContent,
    override val name: String,
    val toolID: String
) : Message {
    override val role: MessageRole
        get() = MessageRole.TOOL
}


