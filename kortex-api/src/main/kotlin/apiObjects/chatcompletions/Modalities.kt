package apiObjects.chatcompletions

import kotlinx.serialization.Serializable

@Serializable
data class Modalities(
    val text: String
)