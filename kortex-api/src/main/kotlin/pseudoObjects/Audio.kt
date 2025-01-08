package pseudoObjects

import kotlinx.serialization.Serializable



@Serializable
/**
 *  Parameters for audio output. Required when audio output is requested with modalities:
 *  ['audio']. We are actively working on this feature to bring cortex as fully
 *  OpenAI compatible platform.
 *  @param format Specifies the output audio format. Must be one of wav, mp3, flac, opus, or pcm16.
 *  @param voice The voice of the generated audio.
 *  @see AudioFormat
 */
data class Audio(
    val format: AudioFormat,
    val voice: String
)
