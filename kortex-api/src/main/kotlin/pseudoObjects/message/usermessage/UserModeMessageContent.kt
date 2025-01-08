package pseudoObjects.message.usermessage

import pseudoObjects.message.MessageRole


interface UserModeMessageContent {
    val type: String
    val name: String
    val role: MessageRole
        get() = MessageRole.USER
}

