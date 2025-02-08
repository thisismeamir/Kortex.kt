package io.github.thisismeamir.kortex.objs.addremotemodel

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletions(
    val template: String,
    val url: String?
)