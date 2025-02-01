package com.thisismeamir.objs.completions

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequest(
    val audio:Map<String, String>,
    val dynatempExponent: Int? = null,
    val dynatempRange: Int? = null,
    val frequencyPenalty: Float? = null,
    val ignoreEos: Boolean? = null,
    val logitBias: Map<String, Int>? = null,
    val logprobs:Boolean? = null,
    val maxCompletionTokens: Int? = null,
    val maxTokens: Int? = null,
    val messages: List<ChatCompletionRequestMessage>,
    val metadata: Map<String, String>? = null,
    val minKeep: Int? = null,
    val minP: Int? = null,
    val mirostat: Boolean? = null,
    val mirostatEta: Int? = null,
    val mirostatTau: Int? = null,
    val modalities: List<String>,
    val model: String,
    val n: Int? = null,
    val nProbs: Int? = null,
    val parallelToolCalls: Boolean? = null,
    val penalizeNl: Boolean? = null,
    val presencePenalty: Float? = null,
    val repeatLastN: Int? = null,
    val repeatPenalty: Int? = null,
    val responseFormat: Map<String, String>,
    val seed: Int? = null,
    val serviceTier: String? = null,
    val stop: List<String>,
    val store: Boolean? = null,
    val stream: Boolean? = null,
    val streamOptions: Map<String, String>? = null,
    val temperature: Float? = null,
    val tfsZ: Int? = null,
    val toolChoice: String,
    val tools: List<ChatCompletionTool>,
    val topK: Int? = null,
    val topLogprobs: Int? = null,
    val topP: Float? = null,
    val typP: Int? = null,
    val user: String? = null
)



