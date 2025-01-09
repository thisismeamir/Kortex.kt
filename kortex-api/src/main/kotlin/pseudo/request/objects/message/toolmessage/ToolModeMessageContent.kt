package pseudo.request.objects.message.toolmessage

import pseudo.request.objects.message.MessageContent

interface ToolModeMessageContent: MessageContent {
    val type: String
}