package objs.addremotemodel

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import objs.updatemodel.InferenceParams

@Serializable
data class AddRemoteModelRequest(
    val TeansformReq: TransformRequest,
    val apiKeyTemplate: String,
    val engine: String,
    val inferenceParams: InferenceParams,
    val metadata: JsonObject,
    val model: String,
    val version: String
)



