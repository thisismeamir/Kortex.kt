package objs.chat.threads

import kotlinx.serialization.Serializable

@Serializable
data class UpdateMetaDataRequest(
    val metadata: ThreadMetadata
)