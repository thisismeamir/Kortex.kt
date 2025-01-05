package apiObjects.chatcompletions.audio

import kotlinx.serialization.Serializable

@Serializable
data class StreamOptions(
    val includeUsage: Boolean
)
