package objs.addremotemodel

import kotlinx.serialization.Serializable

@Serializable
class AddRemoteModelResponse (
    val message: String,
    val model: SimpleModelInformation?
)

