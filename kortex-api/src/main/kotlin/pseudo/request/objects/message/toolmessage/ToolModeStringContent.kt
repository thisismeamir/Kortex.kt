package pseudo.request.objects.message.toolmessage

import kotlinx.serialization.Serializable

@Serializable
data class ToolModeStringContent(val text: String): ToolModeMessageContent {
    override val type: String
        get() = "singleText"
}