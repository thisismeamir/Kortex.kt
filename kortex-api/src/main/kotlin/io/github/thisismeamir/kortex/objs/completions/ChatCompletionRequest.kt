package io.github.thisismeamir.kortex.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequest(
    val audio:Map<String, String> = mapOf("audioKey" to "audioValue"),
    val dynatempExponent: Int? = null,
    val dynatempRange: Int? = null,
    val frequencyPenalty: Float? = null,
    val ignoreEos: Boolean? = null,
    val logitBias: Map<String, Int>? = null,
    val logprobs:Boolean? = null,
    val maxCompletionTokens: Int? = null,
    val maxTokens: Int? = null,
    val messages: List<io.github.thisismeamir.kortex.objs.completions.ChatCompletionRequestMessage>,
    val metadata: Map<String, String>? = null,
    val minKeep: Int? = null,
    val minP: Int? = null,
    val mirostat: Boolean? = null,
    val mirostatEta: Int? = null,
    val mirostatTau: Int? = null,
    val modalities: List<String> = listOf("text"),
    val model: String,
    val n: Int? = null,
    val nProbs: Int? = null,
    val parallelToolCalls: Boolean? = null,
    val penalizeNl: Boolean? = null,
    val presencePenalty: Float? = null,
    val repeatLastN: Int? = null,
    val repeatPenalty: Int? = null,
    val responseFormat: Map<String, String> = mapOf("formatKey" to "formatValue"),
    val seed: Int? = null,
    val serviceTier: String? = null,
    val stop: List<String> = listOf("End"),
    val store: Boolean? = null,
    val stream: Boolean? = null,
    val streamOptions: Map<String, String>? = null,
    val temperature: Float? = null,
    val tfsZ: Int? = null,
    val toolChoice: String? = null,
    val tools: List<ChatCompletionTool>? = null,
    val topK: Int? = null,
    val topLogprobs: Int? = null,
    val topP: Float? = null,
    val typP: Int? = null,
    val user: String? = null
)



