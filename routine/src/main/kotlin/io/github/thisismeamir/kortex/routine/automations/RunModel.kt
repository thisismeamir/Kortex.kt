package io.github.thisismeamir.kortex.routines.automations

import io.github.thisismeamir.kortex.core.Kortex
import io.github.thisismeamir.kortex.core.objs.startmodel.StartModelRequest

suspend fun Kortex.runModel(): Kortex {
    this.startModel(
        StartModelRequest(
        model = this.getFirstModelId()
    )
    )
    return this
}

