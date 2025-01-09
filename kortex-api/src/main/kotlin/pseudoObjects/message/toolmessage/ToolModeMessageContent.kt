package pseudoObjects.message.toolmessage

import pseudoObjects.message.MessageContent

interface ToolModeMessageContent: MessageContent {
    val type: String
}