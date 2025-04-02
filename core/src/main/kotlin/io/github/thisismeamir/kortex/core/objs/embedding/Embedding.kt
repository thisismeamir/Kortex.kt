package io.github.thisismeamir.kortex.core.objs.embedding

import kotlinx.serialization.Serializable

@Serializable
data class Embedding(
    val embedding: List<Float>,
    val index: Int,
    val `object`: String
)


