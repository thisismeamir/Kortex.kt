package apiObjects.chatcompletions

import kotlinx.serialization.Serializable


@Serializable
data class Parameter(val name: String, val value: String)
