package io.github.thisismeamir.kortex.objs.engine

import kotlinx.serialization.Serializable

@Serializable
data class LatestReleasedEngine (
    val createdAt: String,
    val downloadCount: Int,
    val name:String
)