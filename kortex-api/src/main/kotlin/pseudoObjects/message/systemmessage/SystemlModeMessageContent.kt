package pseudoObjects.message.systemmessage

import kotlinx.serialization.Serializable


@Serializable
data class SystemlModeMessageContent(
    val text: String?,
    val textAndType: Pair<String, String>?
)
