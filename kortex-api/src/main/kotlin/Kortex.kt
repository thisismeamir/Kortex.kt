import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import objs.Configuration
import objs.DataListResponse
import objs.DeleteObjectResponse
import objs.MessageResponse
import objs.getmodel.GetModelsResponse
import objs.getmodel.Model
import objs.importmodel.ImportModelRequest
import objs.importmodel.ImportModelResponse
import objs.addremotemodel.AddRemoteModelRequest
import objs.addremotemodel.AddRemoteModelResponse
import objs.addremotemodel.StopModelDownloadResponse
import objs.chat.messages.*
import objs.chat.threads.UpdateMetaDataRequest
import objs.engine.Engine
import objs.engine.InstallEngine
import objs.engine.ReleasedEngine
import objs.engine.VariantRequestBody
import objs.file.File
import objs.hardware.ActivateGpusRequest
import objs.hardware.ActivateGpusResponse
import objs.hardware.HardwareInformation
import objs.pullmodel.PullModelRequest
import objs.pullmodel.PullModelResponse
import objs.removemodel.RemoveModelSourceRequest
import objs.startmodel.StartModelRequest
import objs.updatemodel.UpdateModelRequest
import objs.updatemodel.UpdateModelResponse
import utils.execute
import utils.fixSingleQuotes
import java.nio.file.Path

/**
 * Class to interact with the Cortex server using the REST API
 * @constructor Initializes the Cortex server
 * @see Configuration
 */
class Kortex() {
    init {
//        println("initializing Cortex")
//        val output = execute("cortex start")
//        print("response: $output")
    }

    /**
     * Stop the Cortex server
     */
    fun stop() {
        println("Stopping Cortex")
        val output = execute("cortex stop")
        print("response: $output")
    }

    @OptIn(ExperimentalSerializationApi::class)
    /**
     * JSON object to serialize and deserialize objects
     */
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        namingStrategy = JsonNamingStrategy.SnakeCase
    }


    /**
     * HTTP client to make requests to the Cortex server
     */
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }


    /**
     * Get the configuration of the Cortex server
     * @return Configuration object
     * @see Configuration
     */
    suspend fun getConfiguration(): Configuration {
        val responseHTTP = client.get("http://127.0.0.1:5555/v1/configs")
        val fixedJson = responseHTTP.bodyAsText().fixSingleQuotes()
        return json.decodeFromString(fixedJson)
    }

    /**
     * Update the configuration of the Cortex server
     * @param config: Configuration object
     * @return Pair<Int, String> where the first element is the status code of the response and
     * the second element is the response body
     */
    suspend fun updateConfiguration(config: Configuration): Pair<Int, String> {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/configs") {
            setBody(json.encodeToString(config))
        }
        return Pair(response.status.value, response.bodyAsText())
    }

    /**
     * Get the health of the Cortex server
     * @return Int representing the status code of the response
     */
    suspend fun getServerHealth(): Int {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/healthz")
        return response.status.value
    }

    /**
     * Initiates the shutdown process for the server, ensuring that all active connections are gracefully closed
     * and any ongoing processes are properly terminated.
     */
    suspend fun terminateServer(): Int {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/processManager/destroy")
        return response.status.value
    }

    suspend fun getModels(): GetModelsResponse {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/models")
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<GetModelsResponse>(fixedJson)
    }

    suspend fun startModel(model: StartModelRequest): Pair<Int, String> {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/models/start") {
            contentType(ContentType.Application.Json)
            setBody(model)
        }
        return Pair(response.status.value, response.bodyAsText())
    }

    suspend fun stopModel(model: String): Pair<Int, String> {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/models/stop") {
            contentType(ContentType.Application.Json)
            setBody("{\"model\": \"$model\"}")
        }
        return Pair(response.status.value, response.bodyAsText())
    }

    suspend fun deleteModel(model: String): DeleteObjectResponse {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/v1/models/${model}")
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<DeleteObjectResponse>(fixedJson)
    }

    suspend fun getModel(model: String): Model {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/models/${model}")
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<Model>(fixedJson)
    }

    suspend fun updateModel(model: String, updateRequest: UpdateModelRequest): UpdateModelResponse {
        val response: HttpResponse = client.patch("http://127.0.0.1:5555/v1/models/${model}") {
            contentType(ContentType.Application.Json)
            setBody(updateRequest)
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<UpdateModelResponse>(fixedJson)
    }

    suspend fun addRemoteModel(request: AddRemoteModelRequest): AddRemoteModelResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/models/add") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<AddRemoteModelResponse>(fixedJson)
    }

    suspend fun importModel(request: ImportModelRequest): ImportModelResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/models/import") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<ImportModelResponse>(fixedJson)
    }

    suspend fun stopModelDownload(taskId: String): StopModelDownloadResponse {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/v1/models/pull") {
            contentType(ContentType.Application.Json)
            setBody("{\"taskId\": \"$taskId\"}")
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<StopModelDownloadResponse>(fixedJson)
    }

    suspend fun pullModel(request: PullModelRequest): PullModelResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/models/pull") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<PullModelResponse>(fixedJson)
    }

    suspend fun removeModelSource(request: RemoveModelSourceRequest): MessageResponse {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/v1/models/sources") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<MessageResponse>(fixedJson)
    }

    suspend fun addModelSource(sourcePath: String): MessageResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/models/") {
            contentType(ContentType.Application.Json)
            setBody("{\"source\": \"$sourcePath\"}")
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<MessageResponse>(fixedJson)
    }

    suspend fun getThreads(): List<Thread> {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/threads")
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString<DataListResponse<Thread>>(fixedJson).data
    }

    suspend fun createThread(title: String): Thread {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/threads") {
            contentType(ContentType.Application.Json)
            setBody("\"metadata\": {\"title\": \"$title\"}")
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString(fixedJson)
    }

    suspend fun deleteThread(threadId: String): DeleteObjectResponse {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/v1/threads/$threadId")
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString(fixedJson)
    }

    suspend fun getThread(threadId: String): Thread {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/threads/$threadId")
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString(fixedJson)
    }

    suspend fun updateThreadMetadata(threadId: String, request: UpdateMetaDataRequest): Thread {
        val response: HttpResponse = client.patch("http://127.0.0.1:5555/v1/threads/$threadId") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        val fixedJson = response.bodyAsText().fixSingleQuotes()
        return json.decodeFromString(fixedJson)
    }

    suspend fun getMessages(threadId: String, queryParameters: ListMessagesQueryParameters): List<Message> {
        val response: HttpResponse = client.get("https://127.0.0.1:5555/v1/threads/$threadId/messages") {
            url {
                parameters.append("before", queryParameters.before)
                parameters.append("after", queryParameters.after)
                parameters.append("runId", queryParameters.runId)
                parameters.append("order", queryParameters.order)
                parameters.append("limit", queryParameters.limit.toString())
            }
        }
        return json
            .decodeFromString<DataListResponse<Message>>(
                response.bodyAsText().fixSingleQuotes()
            ).data
    }

    suspend fun createMessage(threadId: String, content: CreateMessageRequestBody): CreateMessageResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/threads/$threadId/messages") {
            contentType(ContentType.Application.Json)
            setBody(content)
        }
        return json.decodeFromString<CreateMessageResponse>(response.bodyAsText())
    }

    suspend fun deleteMessage(threadId: String, messageId: String): DeleteObjectResponse {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/v1/threads/$threadId/messages/$messageId")
        return json.decodeFromString<DeleteObjectResponse>(response.bodyAsText())
    }

    suspend fun retrieveMessage(threadId: String, messageId: String): RetrieveMessageResponse {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/threads/$threadId/messages/$messageId")
        return json.decodeFromString<RetrieveMessageResponse>(response.bodyAsText())
    }

    suspend fun modifyMessageMetadata(threadId: String, messageId: String, metadata: String): Message {
        val response: HttpResponse = client.patch("http://127.0.0.1:5555/v1/threads/$threadId/messages/$messageId") {
            contentType(ContentType.Application.Json)
            setBody("{\"metadata\": {$metadata} }")
        }
        return json.decodeFromString<Message>(response.bodyAsText())
    }

    suspend fun getHardware(): HardwareInformation {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/hardware")
        return json.decodeFromString<HardwareInformation>(response.bodyAsText())
    }

    suspend fun activateGpus(gpus: ActivateGpusRequest): ActivateGpusResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/hardware/activate") {
            contentType(ContentType.Application.Json)
            setBody(gpus)
        }
        return json.decodeFromString<ActivateGpusResponse>(response.bodyAsText())
    }

    suspend fun getFiles(): List<File> {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/files")
        return json.decodeFromString<DataListResponse<File>>(response.bodyAsText()).data
    }

    suspend fun uploadFile(filePath: String, purpose: String): File {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/files") {
            contentType(ContentType.MultiPart.FormData)
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("file", java.io.File(filePath).readBytes(), Headers.build {
                            append(
                                HttpHeaders.ContentDisposition,
                                "form-data; name=\"file\"; filename=\"${java.io.File(filePath).name}\""
                            )
                        })
                        append("purpose", purpose)
                    }
                ))
        }

        return json.decodeFromString<File>(response.bodyAsText())
    }

    suspend fun deleteFile(fileId: String): DeleteObjectResponse {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/v1/files/$fileId")
        return json.decodeFromString<DeleteObjectResponse>(response.bodyAsText())
    }

    suspend fun retrieveFile(fileId: String): File {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/files/$fileId")
        return json.decodeFromString<File>(response.bodyAsText())
    }

    suspend fun getFileContent(fileId: String, savePath: Path, thread: String? = null) {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/files/$fileId/content") {
            thread?.let {
                url {
                    parameters.append("thread", it.toString())
                }
            }
        }
        java.io.File(savePath.toString()).writeBytes(response.body<ByteArray>())
    }

    suspend fun getInstalledEngines(engineName: String): List<Engine> {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/engines/$engineName")
        return json.decodeFromString<List<Engine>>(response.bodyAsText())
    }

    suspend fun getDefaultEngine(engineName: String): Engine {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/engines/$engineName/default")
        return json.decodeFromString<Engine>(response.bodyAsText())
    }

    suspend fun setDefaultEngineVariant(engineName: String, variant: VariantRequestBody): MessageResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/engines/$engineName/default") {
            contentType(ContentType.Application.Json)
            setBody(variant)
        }
        return json.decodeFromString<MessageResponse>(response.bodyAsText())
    }

    suspend fun uninstallEngine(engineName:String, variant: VariantRequestBody): MessageResponse {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/v1/engines/$engineName/install") {
            contentType(ContentType.Application.Json)
            setBody(variant)
        }
        return json.decodeFromString<MessageResponse>(response.bodyAsText())
    }

    suspend fun installEngine(engineName: String, specification: InstallEngine): MessageResponse {
        val response: HttpResponse =client.post("http://127.0.0.1:5555/v1/engines/$engineName/install") {
            contentType(ContentType.Application.Json)
            setBody(specification)
        }
        return json.decodeFromString<MessageResponse>(response.bodyAsText())
    }

    suspend fun unloadEngine(engineName: String): MessageResponse {
        val response: HttpResponse = client.delete("http://127.0.0.1:5555/v1/engines/$engineName/load")
        return json.decodeFromString<MessageResponse>(response.bodyAsText())
    }

    suspend fun loadEngine(engineName: String): MessageResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/engines/$engineName/load")
        return json.decodeFromString<MessageResponse>(response.bodyAsText())
    }

    suspend fun getReleasedEngines(engineName: String): List<ReleasedEngine> {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/engines/$engineName/releases")
        return json.decodeFromString<List<ReleasedEngine>>(response.bodyAsText())
    }

    suspend fun getLatestEngineRelease(engineName:String): List<ReleasedEngine> {
        val response: HttpResponse = client.get("http://127.0.0.1:5555/v1/engines/$engineName/releases/latest")
        return json.decodeFromString<List<ReleasedEngine>>(response.bodyAsText())
    }

    suspend fun updateEngine(engineName: String): MessageResponse {
        val response: HttpResponse = client.post("http://127.0.0.1:5555/v1/engines/$engineName/update")
        return json.decodeFromString<MessageResponse>(response.bodyAsText())
    }
}


