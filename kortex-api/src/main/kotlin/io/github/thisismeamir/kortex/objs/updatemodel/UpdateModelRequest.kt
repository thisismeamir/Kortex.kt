package io.github.thisismeamir.kortex.objs.updatemodel

import kotlinx.serialization.Serializable

@Serializable
data class UpdateModelRequest(
    val apiKeyTemplate: String,
    val ctxLen: Int,
    val dynatempExponent: Double,
    val dynatempRange: Double,
    val engine: String,
    val files: List<String>,
    val frequencyPenalty: Double,
    val ignoreEos: Boolean,
    val inferenceParams: InferenceParams,
    val maxTokens: Int,
    val minKeep: Int,
    val minP: Double,
    val mirostat: Boolean,
    val mirostatEta: Double,
    val mirostatTau: Double,
    val nParallel: Int,
    val nProbs: Int,
    val ngl: Int,
    val penalizeNl: Boolean,
    val presencePenalty: Double,
    val promptTemplate: String,
    val repeatLastN: Int,
    val repeatPenalty: Double,
    val seed: Int,
    val stop: List<String>,
    val stream: Boolean,
    val temperature: Double,
    val tfsZ: Double,
    val topK: Int,
    val topP: Double,
    val typP: Double,
    val version: String
)

