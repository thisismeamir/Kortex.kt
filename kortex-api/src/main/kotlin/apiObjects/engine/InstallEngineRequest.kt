package apiObjects.engine

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to install an engine.
 * @param api_key The API key for authentication.
 * @param metadata The metadata associated with the engine.
 * @param type The type of the engine.
 * @param url The URL to download the engine.
 * @param variant The variant of the engine (optional).
 * @param version The version of the engine (optional).
 */
@Serializable
data class InstallEngineRequest(
    val api_key: String,
    val metadata: Map<String, String>,
    val type: String,
    val url: String,
    val variant: String? = null,
    val version: String? = null
)