package pseudo.request.objects.tool

import kotlinx.serialization.Serializable

@Serializable
/**
 * Tool
 */
data class Tool(
    val function: ToolFunction,
    val type: String
)