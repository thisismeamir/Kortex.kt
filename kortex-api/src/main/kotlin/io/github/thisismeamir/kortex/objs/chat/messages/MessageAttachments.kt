package io.github.thisismeamir.kortex.objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class MessageAttachments(
    val fileId: String?,
    val tools: List<io.github.thisismeamir.kortex.objs.chat.messages.MessageToolsInRetrieveMessage>

)