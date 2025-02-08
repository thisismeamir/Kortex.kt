package io.github.thisismeamir.kortex.objs.file

import kotlinx.serialization.Serializable


@Serializable
data class File(
   val bytes: Long,
    val createdAt: Long,
    val filename: String,
    val id: String,
    val `object`: String = "file",
    val purpose: String
)


