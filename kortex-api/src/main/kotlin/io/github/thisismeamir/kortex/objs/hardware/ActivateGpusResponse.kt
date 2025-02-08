package io.github.thisismeamir.kortex.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class ActivateGpusResponse(
    val activatedGpus: List<Int>?,
    val message: String
)