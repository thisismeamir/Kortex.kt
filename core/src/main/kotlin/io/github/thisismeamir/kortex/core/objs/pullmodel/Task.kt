package io.github.thisismeamir.kortex.core.objs.pullmodel

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String,
    val items: List<TaskItem>,
    val type: String
)