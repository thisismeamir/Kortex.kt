package apiObjects.chatcompletions

import apiObjects.chatcompletions.audio.Audio
import apiObjects.chatcompletions.audio.StreamOptions
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequest(
    val audio: Audio,
    val dynatempExponent: Int,
    val dynatempRange: Int,
    val frequencyPenalty: Double,
    val ignoreEos: Boolean,
    val logitBias: LogitBias,
    val logprobs: Boolean,
    val maxCompletionTokens: Int,
    val maxTokens: Int,
    val messages: List<Message>,
    val metadata: Metadata,
    val minKeep: Int,
    val minP: Int,
    val mirostat: Boolean,
    val mirostatEta: Int,
    val mirostatTau: Int,
    val modalities: Modalities,
    val model: String,
    val n: Int,
    val nProbs: Int,
    val parallelToolCalls: Boolean,
    val penalizeNl: Boolean,
    val presencePenalty: Double,
    val repeatLastN: Int,
    val repeatPenalty: Double,
    val responseFormat: ResponseFormat,
    val seed: Int,
    val serviceTier: String,
    val stop: Map<String, String>,
    val store: Boolean,
    val stream: Boolean,
    val streamOptions: StreamOptions?,
    val temperature: Double,
    val tfsZ: Int,
    val toolChoice: String,
    val tools: List<Tool>,
    val topK: Int,
    val topLogprobs: Int,
    val topP: Double,
    val typP: Int,
    val user: String
)