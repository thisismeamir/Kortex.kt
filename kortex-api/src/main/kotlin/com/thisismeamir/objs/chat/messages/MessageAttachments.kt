package com.thisismeamir.objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class MessageAttachments(
    val fileId: String?,
    val tools: List<MessageToolsInRetrieveMessage>

)