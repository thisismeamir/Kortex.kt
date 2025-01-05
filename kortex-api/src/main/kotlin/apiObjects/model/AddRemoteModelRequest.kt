package apiObjects.model

import apiObjects.chatcompletions.TransformReq
import apiObjects.chatcompletions.TransformResp
import kotlinx.serialization.Serializable

/**
 * Data class representing a request to add a remote model.
 * @param TransformReq The transformation request configuration.
 * @param TransformResp The transformation response configuration.
 * @param api_key_template The template for the API key.
 * @param engine The engine used for the model.
 * @param inference_params The inference parameters.
 * @param metadata The metadata for the remote model.
 * @param model The model identifier.
 * @param version The version of the model.
 */
@Serializable
data class AddRemoteModelRequest(
    val TransformReq: TransformReq,
    val TransformResp: TransformResp,
    val api_key_template: String,
    val engine: String,
    val inference_params: InferenceParams,
    val metadata: AddRemoteModelMetadata,
    val model: String,
    val version: String
)