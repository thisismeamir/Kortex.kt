package io.github.thisismeamir.kortex.objs.chat.messages

import kotlinx.serialization.Serializable


@Serializable
data class Message(
    val content: io.github.thisismeamir.kortex.objs.chat.messages.MessageContent,
    val createdAt: Long,
    val id: String,
    val metadata: String,
    val `object`: String = "thread.message",
    val role: String,
    val status: String,
    val threadId: String
)


