package objs.startmodel

import kotlinx.serialization.Serializable


@Serializable
data class StartModelRequest (
    val model: String,
    val mmproj: String = "",
    val ctxLen: Int = 4096,
    val cachingEnabled: Boolean = true,
    val cachingType: String = "f16",
    val modelPath: String = "",
    val nParallel: Int = 1,
    val ngl: Double = 32.0
)
