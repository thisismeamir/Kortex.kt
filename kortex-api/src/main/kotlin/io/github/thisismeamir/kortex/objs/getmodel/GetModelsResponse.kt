package io.github.thisismeamir.kortex.objs.getmodel

import kotlinx.serialization.Serializable

@Serializable
data class GetModelsResponse(
    val data: List<io.github.thisismeamir.kortex.objs.getmodel.Model>,
    val `object`: String,
    val result: String
)