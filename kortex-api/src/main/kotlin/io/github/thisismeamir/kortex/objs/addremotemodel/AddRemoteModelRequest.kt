package io.github.thisismeamir.kortex.objs.addremotemodel

import io.github.thisismeamir.kortex.objs.updatemodel.InferenceParams
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AddRemoteModelRequest(
    val TeansformReq: io.github.thisismeamir.kortex.objs.addremotemodel.TransformRequest,
    val apiKeyTemplate: String,
    val engine: String,
    val inferenceParams: InferenceParams,
    val metadata: JsonObject,
    val model: String,
    val version: String
)



