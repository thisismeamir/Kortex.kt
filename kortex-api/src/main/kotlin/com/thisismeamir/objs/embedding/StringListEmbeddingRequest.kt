package com.thisismeamir.objs.embedding

import kotlinx.serialization.Serializable

@Serializable
data class StringListEmbeddingRequest(
    override val embeddingFormat: String,
    override val input: List<String>,
    override val `object`: String
) : CreateEmbeddingRequest