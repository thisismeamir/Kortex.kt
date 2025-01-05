package apiObjects.chatcompletions

import kotlinx.serialization.Serializable

@Serializable
data class Function(
    val description: String,
    val name: String,
    val parameters: Map<String, List<Parameter>>,
    val strict: Boolean
)