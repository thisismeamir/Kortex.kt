package io.github.thisismeamir.kortex.core.objs

import kotlinx.serialization.Serializable

@Serializable
data class DeleteObjectResponse(
    val deleted: Boolean,
    val id: String,
    val `object`: String
)