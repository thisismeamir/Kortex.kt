package apiObjects.chatcompletions

import kotlinx.serialization.Serializable

@Serializable
data class ResponseFormat(
    val type: String
)