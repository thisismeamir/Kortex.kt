package pseudoObjects.message.assistantmessage

import pseudoObjects.message.MessageContent

interface AssistantModeContent: MessageContent {
    val type: String
}