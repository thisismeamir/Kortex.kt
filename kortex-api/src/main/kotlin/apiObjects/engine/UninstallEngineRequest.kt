package apiObjects.engine

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to uninstall an engine.
 * @param variant The variant of the engine (optional).
 * @param version The version of the engine (optional).
 */
@Serializable
data class UninstallEngineRequest(
    val variant: String? = null,
    val version: String? = null
)