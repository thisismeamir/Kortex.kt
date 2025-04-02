package io.github.thisismeamir.kortex.routines.automations

import io.github.thisismeamir.kortex.core.Kortex

suspend fun Kortex.stopRunning(): Kortex {
    this.stopModel(
        model = this.runningModel
    )
    return this
}