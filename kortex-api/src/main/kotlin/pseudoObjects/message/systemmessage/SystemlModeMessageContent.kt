package pseudoObjects.message.systemmessage

import kotlinx.serialization.Serializable
import pseudoObjects.message.MessageContent


@Serializable
data class SystemlModeMessageContent(
    val text: String?,
    val textAndType: Pair<String, String>?
): MessageContent
