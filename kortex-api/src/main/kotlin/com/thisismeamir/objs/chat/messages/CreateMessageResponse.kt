package com.thisismeamir.objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageResponse(
    val completedAt: Long,
    val content: List<TextMessageContent>,
    val createdAt: Long,
    val id: String,
    val metadata: Map<String, String>,
    val `object`: String,
    val role: String,
    val status: String,
    val threadId: String,
)