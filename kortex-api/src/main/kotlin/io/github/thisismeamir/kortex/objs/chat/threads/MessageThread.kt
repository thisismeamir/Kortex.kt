package io.github.thisismeamir.kortex.objs.chat.threads

import kotlinx.serialization.Serializable

@Serializable
data class MessageThread(
    val createdAt: Long,
    val id: String,
    val metadata: Map<String, String>,
    val `object`: String = "thread"
)




