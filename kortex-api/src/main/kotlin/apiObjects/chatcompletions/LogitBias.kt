package apiObjects.chatcompletions

import kotlinx.serialization.Serializable

@Serializable
data class LogitBias(
    val token: Map<String, Int>
)