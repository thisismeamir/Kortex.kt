package pseudoObjects.message.usermessage

import pseudoObjects.message.MessageContent
import pseudoObjects.message.MessageRole


interface UserModeMessageContent: MessageContent {
    val type: String
    val name: String
    val role: MessageRole
        get() = MessageRole.USER
}

