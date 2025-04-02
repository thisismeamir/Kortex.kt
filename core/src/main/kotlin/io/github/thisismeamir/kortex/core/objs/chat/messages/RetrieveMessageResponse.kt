package io.github.thisismeamir.kortex.core.objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class RetrieveMessageResponse(
    val attachments: List<MessageAttachments>,
    val content: List<MessageContent>,
    val createdAt: Long,
    val id: String,
    val metadata: String,
    val `object`: String,
    val role: String,
    val status: String,
    val threadId: String
)