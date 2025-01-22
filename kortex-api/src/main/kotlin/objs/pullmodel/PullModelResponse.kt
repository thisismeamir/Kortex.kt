package objs.pullmodel

import kotlinx.serialization.Serializable

@Serializable
data class PullModelResponse (
    val message: String,
    val task: Task
)