package pseudo.request.objects.message.usermessage

import kotlinx.serialization.Serializable

@Serializable
data class AudioContent(
    val data: String,
    val format: UserModeAudioFormat,
    override val name: String
): UserModeMessageContent {
    override val type: String
        get() = "input_audio"
}