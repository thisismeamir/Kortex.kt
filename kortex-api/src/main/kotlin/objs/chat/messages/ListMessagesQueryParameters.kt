package objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class ListMessagesQueryParameters(
    val before: String,
    val after: String,
    val runId: String,
    val order: String,
    val limit: Int
)