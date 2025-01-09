package pseudo.request.objects.message.assistantmessage

import pseudo.request.objects.message.MessageContent

interface AssistantModeContent: MessageContent {
    val type: String
}