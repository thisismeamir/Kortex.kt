package pseudo.response.objects

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMessage(
    val audio: ResponseAudio,
    val content: String?,
    val functionCall: FunctionCall,
    val refusal: String?,
    val role: String,
    val toolCalls: List<ToolCall>
)