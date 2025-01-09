package pseudo.request.objects.message.assistantmessage

import kotlinx.serialization.Serializable

@Serializable
data class AssistantModeTextContent(val text: String) :
    AssistantModeContent {
    override val type: String
        get() = "text"
}