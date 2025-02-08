package io.github.thisismeamir.kortex.objs.chat.threads

import kotlinx.serialization.Serializable

@Serializable
data class ThreadMetadata (
    val title: String,
    val lastMessage: String
)