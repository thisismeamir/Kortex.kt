package objs.removemodel

import kotlinx.serialization.Serializable

@Serializable
data class RemoveModelSourceReponse (
    val message: String?,
    val error: String?
)