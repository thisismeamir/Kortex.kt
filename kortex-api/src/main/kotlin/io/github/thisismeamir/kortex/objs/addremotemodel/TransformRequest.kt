package io.github.thisismeamir.kortex.objs.addremotemodel

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class TransformRequest(
    val chatCompletions: io.github.thisismeamir.kortex.objs.addremotemodel.ChatCompletions,
    val embeddings: JsonObject,
    val getModels: JsonObject
)