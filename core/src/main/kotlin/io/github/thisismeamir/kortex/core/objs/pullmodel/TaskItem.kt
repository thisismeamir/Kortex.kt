package io.github.thisismeamir.kortex.core.objs.pullmodel

import kotlinx.serialization.Serializable

@Serializable
data class TaskItem (
    val bytes: Long,
    val checksum: String,
    val downloadUrl: String,
    val downloadedBytes: Long,
    val id: String,
    val localPath: String
)