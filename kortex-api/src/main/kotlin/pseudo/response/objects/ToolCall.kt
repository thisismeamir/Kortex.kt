package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class ToolCall(
    val function: FunctionCall,
    val id: String,
    val type: String
)