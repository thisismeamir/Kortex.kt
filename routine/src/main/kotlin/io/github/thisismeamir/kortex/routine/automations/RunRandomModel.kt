package io.github.thisismeamir.kortex.routines.automations

import io.github.thisismeamir.kortex.core.Kortex
import io.github.thisismeamir.kortex.core.objs.startmodel.StartModelRequest

suspend fun Kortex.runRandomModel(): Kortex {
    this.startModel(
        StartModelRequest(
            model = this.getRandomModelId()
        )
    )
    return this
}