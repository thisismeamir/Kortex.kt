package objs.getmodel

import kotlinx.serialization.Serializable

@Serializable
data class GpuRecommendation(
    val contextLength: Int,
    val ngl: Int,
    val ram: Int,
    val recommendNgl: Int,
    val vram: Int
)