package objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class DeleteMessageResponse(
    val deleted: Boolean,
    val id: String,
    val `object`: String
)