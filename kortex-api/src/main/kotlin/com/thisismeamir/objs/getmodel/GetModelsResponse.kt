package com.thisismeamir.objs.getmodel

import kotlinx.serialization.Serializable

@Serializable
data class GetModelsResponse(
    val data: List<Model>,
    val `object`: String,
    val result: String
)