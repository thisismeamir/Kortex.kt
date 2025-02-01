package objs.engine

import kotlinx.serialization.Serializable

@Serializable
data class LatestReleasedEngine (
    val createdAt: String,
    val downloadCount: Int,
    val name:String
)