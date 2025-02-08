package io.github.thisismeamir.kortex.objs.getmodel

import kotlinx.serialization.Serializable

@Serializable
data class Recommendation(
    val cpuMode: io.github.thisismeamir.kortex.objs.getmodel.CpuRecommendation,
    val gpuMode: List<GpuRecommendation>
)