package io.github.thisismeamir.kortex.objs.embedding

import kotlinx.serialization.Serializable

@Serializable
data class IntListListEmbeddingRequest(
    override val embeddingFormat: String,
    override val input: List<List<Int>>,
    override val `object`: String
) : io.github.thisismeamir.kortex.objs.embedding.CreateEmbeddingRequest