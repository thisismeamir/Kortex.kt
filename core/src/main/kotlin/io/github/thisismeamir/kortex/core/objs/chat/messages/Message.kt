package io.github.thisismeamir.kortex.core.objs.chat.messages

import kotlinx.serialization.Serializable


@Serializable
data class Message(
    val content: MessageContent,
    val createdAt: Long,
    val id: String,
    val metadata: String,
    val `object`: String = "thread.message",
    val role: String,
    val status: String,
    val threadId: String
)


