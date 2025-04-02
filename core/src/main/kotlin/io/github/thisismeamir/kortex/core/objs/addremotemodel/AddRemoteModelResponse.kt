package io.github.thisismeamir.kortex.core.objs.addremotemodel

import kotlinx.serialization.Serializable

@Serializable
class AddRemoteModelResponse (
    val message: String,
    val model: SimpleModelInformation?
)

