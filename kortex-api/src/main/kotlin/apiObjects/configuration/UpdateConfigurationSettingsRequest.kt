package apiObjects.configuration

import kotlinx.serialization.Serializable

/**
 * Data class representing a request to update configuration settings.
 * @param allowedOrigins The list of allowed origins (optional).
 * @param cors Whether CORS is enabled (optional).
 * @param huggingfaceToken The HuggingFace token to pull models (optional).
 * @param noProxy The list of hosts that should not be proxied (optional).
 * @param proxyPassword The password for the proxy server (optional).
 * @param proxyUrl The URL for the proxy server (optional).
 * @param proxyUsername The username for the proxy server (optional).
 * @param verifyHostSsl Whether to verify the SSL certificate of the host (optional).
 * @param verifyPeerSsl Whether to verify the SSL certificate of the peer (optional).
 * @param verifyProxyHostSsl Whether to verify the SSL certificate of the proxy server host (optional).
 * @param verifyProxySsl Whether to verify the SSL certificate of the proxy server (optional).
 */
@Serializable
data class UpdateConfigurationSettingsRequest(
    val allowedOrigins: List<String>? = null,
    val cors: Boolean? = null,
    val huggingfaceToken: String? = null,
    val noProxy: String? = null,
    val proxyPassword: String? = null,
    val proxyUrl: String? = null,
    val proxyUsername: String? = null,
    val verifyHostSsl: Boolean? = null,
    val verifyPeerSsl: Boolean? = null,
    val verifyProxyHostSsl: Boolean? = null,
    val verifyProxySsl: Boolean? = null
)