package io.github.thisismeamir.kortex.objs.removemodel

import kotlinx.serialization.Serializable

@Serializable
data class RemoveModelSourceRequest(
    val source: String
)

