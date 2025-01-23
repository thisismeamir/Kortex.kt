package objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponseMessageToolCall(
    val function: ChatCompletionResponseMessageToolCallFunction,
    val id: String,
    val type: String
)