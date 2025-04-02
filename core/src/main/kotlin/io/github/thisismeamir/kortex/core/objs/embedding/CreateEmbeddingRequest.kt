package io.github.thisismeamir.kortex.core.objs.embedding

import kotlinx.serialization.Serializable

@Serializable
data class EmbeddingRequest<T> (
    val embeddingFormat: String,
    val input: T,
    val model: String
)

