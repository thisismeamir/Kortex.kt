package apiObjects.chatcompletions.audio

import kotlinx.serialization.Serializable

@Serializable
data class Audio(
    val format: String,
    val voice: String
)