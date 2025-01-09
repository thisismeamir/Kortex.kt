package pseudo.request.objects.message.systemmessage

import kotlinx.serialization.Serializable
import pseudo.request.objects.message.MessageContent


@Serializable
data class SystemlModeMessageContent(
    val text: String?,
    val textAndType: Pair<String, String>?
): MessageContent
