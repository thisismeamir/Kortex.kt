package apiObjects.chatcompletions

import kotlinx.serialization.Serializable

@Serializable
data class Tool(
    val function: Function,
    val type: String
)