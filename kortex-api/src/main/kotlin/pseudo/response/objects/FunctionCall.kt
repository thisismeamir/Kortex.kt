package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class FunctionCall(
    val arguments: String,
    val name: String
)