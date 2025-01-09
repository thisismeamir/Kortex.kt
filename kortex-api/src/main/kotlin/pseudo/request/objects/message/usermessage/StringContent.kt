package pseudo.request.objects.message.usermessage

import kotlinx.serialization.Serializable

@Serializable
data class StringContent(
    val text: String,
    override val name: String
) : pseudo.request.objects.message.usermessage.UserModeMessageContent {
    override val type: String
        get() = "string"
}