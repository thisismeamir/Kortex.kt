package apiObjects.chatcompletions

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val content: String,
    val name: String,
    val role: String
)