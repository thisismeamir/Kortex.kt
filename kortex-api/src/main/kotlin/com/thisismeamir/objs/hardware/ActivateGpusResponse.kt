package com.thisismeamir.objs.hardware

import kotlinx.serialization.Serializable

@Serializable
data class ActivateGpusResponse(
    val activatedGpus: List<Int>?,
    val message: String
)