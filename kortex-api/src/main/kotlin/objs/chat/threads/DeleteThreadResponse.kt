package objs.chat.threads

import kotlinx.serialization.Serializable

@Serializable
data class DeleteThreadResponse(
    val deleted: Boolean,
    val id: String,
    val `object`: String = "thread.deleted"
)