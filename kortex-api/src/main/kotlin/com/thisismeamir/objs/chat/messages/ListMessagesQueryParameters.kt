package com.thisismeamir.objs.chat.messages

import kotlinx.serialization.Serializable

@Serializable
data class ListMessagesQueryParameters(
    val before: String? = null,
    val after: String? = null,
    val runId: String? = null,
    val order: String? = null,
    val limit: Int? = null
)