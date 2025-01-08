package pseudoObjects.message.assistantmessage

import kotlinx.serialization.Serializable

@Serializable
data class AssistantmodeRefusalContent(val refusal: String, override val type: String) : AssistantModeContent