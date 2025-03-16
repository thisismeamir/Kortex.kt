package io.github.thisismeamir.kortex.core.objs.removemodel

import kotlinx.serialization.Serializable

@Serializable
data class RemoveModelSourceRequest(
    val source: String
)

