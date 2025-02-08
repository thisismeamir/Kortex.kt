package io.github.thisismeamir.kortex.objs

import kotlinx.serialization.Serializable

@Serializable
/**
 * Configuration Json for read and writing configurations of Cortex server.
 *
 * @property allowedOrigins List of allowed origins for CORS.
 * @property cors Boolean flag to enable or disable CORS.
 * @property huggingfaceToken Token for Hugging Face API.
 * @property noProxy No proxy setting.
 * @property proxyPassword Password for proxy authentication.
 * @property proxyUrl URL of the proxy server.
 * @property proxyUsername Username for proxy authentication.
 * @property verifyHostSsl Boolean flag to verify host SSL.
 * @property verifyPeerSsl Boolean flag to verify peer SSL.
 * @property verifyProxyHostSsl Boolean flag to verify proxy host SSL.
 * @property verifyProxySsl Boolean flag to verify proxy SSL.
 */
data class Configuration(
    var allowedOrigins: List<String>,
    var cors: Boolean,
    var huggingfaceToken: String,
    var noProxy: String,
    var proxyPassword: String,
    var proxyUrl: String,
    var proxyUsername: String,
    var verifyHostSsl: Boolean,
    var verifyPeerSsl: Boolean,
    var verifyProxyHostSsl: Boolean,
    var verifyProxySsl: Boolean
)
