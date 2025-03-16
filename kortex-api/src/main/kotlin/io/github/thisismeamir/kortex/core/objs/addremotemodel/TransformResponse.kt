package io.github.thisismeamir.kortex.core.objs.addremotemodel

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class TransformResponse(
    val chatCompletions: ChatCompletions,
    val embeddings: JsonObject
)