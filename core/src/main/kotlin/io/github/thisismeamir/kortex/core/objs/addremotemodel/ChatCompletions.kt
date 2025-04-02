package io.github.thisismeamir.kortex.core.objs.addremotemodel

import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletions(
    val template: String,
    val url: String?
)