package pseudo.request.objects.message.usermessage

import kotlinx.serialization.Serializable

@Serializable
data class ImageContent(
    val url: String,
    val detail: String,
    override val name: String
) : UserModeMessageContent {
    override val type: String
        get() = "image_url"
}