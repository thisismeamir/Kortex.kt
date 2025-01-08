package pseudoObjects.message.usermessage

import kotlinx.serialization.Serializable

@Serializable
data class TextContent(
    val text: String,
    override val name: String
) : UserModeMessageContent {
    override val type: String
        get() = "text"
}