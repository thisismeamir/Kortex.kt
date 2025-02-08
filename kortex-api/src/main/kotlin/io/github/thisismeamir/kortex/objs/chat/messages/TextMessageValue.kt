package io.github.thisismeamir.kortex.objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class TextMessageValue(
    val annotations: List<String>,
    val value: String,
)