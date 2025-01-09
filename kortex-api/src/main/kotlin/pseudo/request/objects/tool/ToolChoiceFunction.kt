package pseudo.request.objects.tool

import kotlinx.serialization.Serializable

@Serializable
/**
 * Tool Choice Function
 */
data class ToolChoiceFunction(
    val type: String = "function",
    val functionName: String,
) : ToolChoice