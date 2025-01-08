package pseudoObjects.message.assistantmessage

import kotlinx.serialization.Serializable

/**
 * Data about a previous audio response from the model.
 * @param id identifier of the audio response
 */
@Serializable
data class AssistantModeAudio(
    val id: String
)