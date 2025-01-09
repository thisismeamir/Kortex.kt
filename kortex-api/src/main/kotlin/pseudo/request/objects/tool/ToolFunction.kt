package pseudo.request.objects.tool

import kotlinx.serialization.Serializable

@Serializable
/**
 * Tool Function
 */
data class ToolFunction (
    val description: String,
    val name: String,
    val parameters: Map<String, String>
)