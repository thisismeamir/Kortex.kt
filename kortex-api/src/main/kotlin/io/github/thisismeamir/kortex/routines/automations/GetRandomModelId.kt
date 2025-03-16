package io.github.thisismeamir.kortex.routines.automations

import io.github.thisismeamir.kortex.core.Kortex

suspend fun Kortex.getRandomModelId(): String {
    val models = this.getAllModels()
    val randomIndex = (0 until models.size).random()
    return models[randomIndex].id
}