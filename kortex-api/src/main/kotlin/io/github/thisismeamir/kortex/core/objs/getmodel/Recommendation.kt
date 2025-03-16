package io.github.thisismeamir.kortex.core.objs.getmodel

import kotlinx.serialization.Serializable

@Serializable
data class Recommendation(
    val cpuMode: CpuRecommendation,
    val gpuMode: List<GpuRecommendation>
)