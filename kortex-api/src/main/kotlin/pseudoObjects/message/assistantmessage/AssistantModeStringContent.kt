package pseudoObjects.message.assistantmessage

import kotlinx.serialization.Serializable

@Serializable
data class AssistantModeStringContent(val text: String) : AssistantModeContent {
    override val type: String
        get() = "singleText"
}