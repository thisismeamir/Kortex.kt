package objs.engine

import kotlinx.serialization.Serializable

@Serializable
data class ReleasedEngine(
    val draft: Boolean,
    val name: String,
    val prerelease: Boolean,
    val publishedAt: String,
    val url: String
)

