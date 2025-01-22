package objs.addremotemodel

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class TransformRequest(
    val chatCompletions: ChatCompletions,
    val embeddings: JsonObject,
    val getModels: JsonObject
)