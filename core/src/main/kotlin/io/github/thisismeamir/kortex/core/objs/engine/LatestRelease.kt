package io.github.thisismeamir.kortex.core.objs.engine

import kotlinx.serialization.Serializable

@Serializable
data class LatestRelease(
    val createdAt: String,
    val downloadCount: Int,
    val name: String,
    val size: Long
)