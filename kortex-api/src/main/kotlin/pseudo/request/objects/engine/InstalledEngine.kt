package pseudo.request.objects.engine

import kotlinx.serialization.Serializable


@Serializable
data class InstalledEngine(val engine: String, val name: String, val version: String)
