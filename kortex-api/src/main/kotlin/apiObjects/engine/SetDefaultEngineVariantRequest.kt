package apiObjects.engine

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to set the default engine variant.
 * @param variant The variant of the engine.
 * @param version The version of the engine.
 */
@Serializable
data class SetDefaultEngineVariantRequest(
    val variant: String,
    val version: String
)