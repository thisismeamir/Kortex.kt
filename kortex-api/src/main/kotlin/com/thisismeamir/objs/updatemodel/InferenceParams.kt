package com.thisismeamir.objs.updatemodel

import kotlinx.serialization.Serializable

@Serializable
data class InferenceParams(
    val frequencyPenalty: Double,
    val maxTokens: Int,
    val presencePenalty: Double,
    val stream: Boolean,
    val temperature: Double,
    val topP: Double
)