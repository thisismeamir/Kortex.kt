package com.thisismeamir.objs.chat.threads

import kotlinx.serialization.Serializable

@Serializable
data class MessageThread(
    val createdAt: Long,
    val id: String,
    val metadata: ThreadMetadata,
    val `object`: String = "thread"
)




