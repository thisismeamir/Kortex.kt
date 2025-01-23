package objs.chat.threads

import kotlinx.serialization.Serializable

@Serializable
data class Thread(
    val createdAt: Long,
    val id: String,
    val metadata: ThreadMetadata,
    val `object`: String = "thread"
)




