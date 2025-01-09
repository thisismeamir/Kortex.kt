package pseudo.request.objects.tool

import kotlinx.serialization.Serializable

@Serializable
/**
 * Tool Choice String
 */
data class ToolChoiceString(
    val type: String = "string",
    val value: String,
    val option: ToolChoiceStringOption
) : ToolChoice