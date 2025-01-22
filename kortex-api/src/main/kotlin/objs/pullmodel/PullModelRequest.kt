package objs.pullmodel

import kotlinx.serialization.Serializable

@Serializable
data class PullModelRequest (
    val id: String,
    val model : String,
    val name: String
)


