package objs.deletemodel

import kotlinx.serialization.Serializable

@Serializable
data class  DeleteModelResponse(
    val `object`: String,
    val id: String,
    val deleted: Boolean
)