package io.github.thisismeamir.kortex.objs.addremotemodel

import kotlinx.serialization.Serializable

@Serializable
data class StopModelDownloadResponse(
    val message: String,
    val taskId: String
)
