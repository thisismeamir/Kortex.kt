package com.thisismeamir.objs

import kotlinx.serialization.Serializable


@Serializable
data class DataListResponse<T>(
    val data: List<T>
)
