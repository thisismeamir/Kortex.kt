package io.github.thisismeamir.kortex.routines.automations

import io.github.thisismeamir.kortex.core.Kortex

suspend fun Kortex.getFirstModelId(): String {

    val modelId = this.getAllModels()[1].id
    return modelId
}