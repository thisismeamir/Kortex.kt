package com.thisismeamir.objs

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val message: String,
)

@Serializable
data class ErrorResponse(
    val error: String
)
