package com.thisismeamir.objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class TextMessageContent(
    val text: TextMessageValue,
    val type: String
) : MessageContent