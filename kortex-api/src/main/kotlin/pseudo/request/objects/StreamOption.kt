package pseudo.request.objects

import kotlinx.serialization.Serializable

@Serializable
/**
 * Options for streaming response. Only set this when you set stream: true.
 * @param includeUsage If set, an additional chunk will be streamed before the data: [DONE] message.
 * The usage field on this chunk shows the token usage statistics for the entire request, and the choices field will always be an empty array.
 * All other chunks will also include a usage field, but with a null value.
 */
data class StreamOption(val includeUsage: Boolean = false)