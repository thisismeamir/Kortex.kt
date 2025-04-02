package io.github.thisismeamir.kortex.core.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class ActivateGpusRequest (
    val gpus: List<Int>
)

