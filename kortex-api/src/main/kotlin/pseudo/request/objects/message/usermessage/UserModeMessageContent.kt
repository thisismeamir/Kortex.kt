package pseudo.request.objects.message.usermessage

import pseudo.request.objects.message.MessageContent
import pseudo.request.objects.message.MessageRole


interface UserModeMessageContent: MessageContent {
    val type: String
    val name: String
    val role: MessageRole
        get() = MessageRole.USER
}

