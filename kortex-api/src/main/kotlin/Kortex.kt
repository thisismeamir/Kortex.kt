import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import responseObjects.StartModelResponse
import utils.CortexLogLevel
import utils.executeCommand
import utils.stopCortexCpp
import java.io.File
import kotlin.properties.Delegates
import kotlinx.serialization.json.decodeFromJsonElement
import pseudo.request.objects.EmbeddingRequest
import pseudo.request.objects.embedding.EmbeddingResponse
import pseudo.request.objects.engine.InstallEngineRequest
import pseudo.request.objects.engine.SetDefaultEngineVariantRequest
import pseudo.request.objects.engine.UninstallEngineRequest
import requestObjects.ChatCompletionRequest
import responseObjects.ChatCompletionResponse

class Kortex {

    var port by Delegates.notNull<Int>()
    lateinit var logLevel: String

    /** Starting the cortex.cpp application using terminal command execution.
     * If cortex.cpp is already initiated a server on your computer
     * you can skip this step and directly use the API class.
     * @param port: The port to start the server on
     * @param logLevel: The log level of the server
     * @return: Unit
     */
    constructor(port: Int, logLevel: CortexLogLevel) {
        executeCommand("cortex start -p $port --loglevel ${logLevel.name}")
        this.port = port
        this.logLevel = logLevel.name
    }

    /**
     * This is another constructor that simply does nothing.
     * This can be used when cortex is already running and you don't want to start it again.
     * @return: Unit
     */
    constructor() {}

    /**
     * This is the main function that starts the server.
     * @param args: The arguments passed to the main function. (Right now does nothing)
     * @return: Unit
     */
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Kortex(5555, CortexLogLevel.DEBUG)
        }
    }

    /**
     * variable is a Ktor HTTP Client that is used to make requests to the cortex server.
     */
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { prettyPrint = true; ignoreUnknownKeys = true })
        }
    }

    /**
     * This function sends a POST request to the server to create a chat completion.
     * @param request ChatCompletionRequest Object
     * @see ChatCompletionRequest
     * @return ChatCompletionResponse Object
     */
    suspend fun createChatCompletion(
        request: ChatCompletionRequest
    ): ChatCompletionResponse {
        val response = client.post("http://127.0.0.1:${this.port}/v1/chat/completions") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return Json.decodeFromString<ChatCompletionResponse>(response.bodyAsText())
    }

    /**
     * This function sends a POST request to the server to create an embedding.
     * @param encodingFormat: The encoding format
     * @param input: The input object
     * @param model: The model
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     * @see: apiObjects.embedding.EmbeddingRequest
     */
    suspend fun createEmbedding(
        request: EmbeddingRequest
    ): List<EmbeddingResponse> {
        val response = client.post("http://127.0.0.1:${this.port}/v1/embeddings") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }

        return Json.decodeFromString<List<EmbeddingResponse>>(response.bodyAsText())

    }

    /**
     * This function sends a GET request to the server to list all installed engines.
     * @param name: The name of the engine
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun listInstalledEngines(name: String): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/engines/$name") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a GET request to the server to retrieve the default engine variant for the specified engine type.
     * @param name: The name of the engine
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun getDefaultEngineVariant(name: String): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/engines/$name/default") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a POST request to the server to set the default engine variant for the specified engine type.
     * @param name: The name of the engine
     * @param variant: The variant of the engine
     * @param version: The version of the engine variant
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun setDefaultEngineVariant(name: String, variant: String, version: String): HttpResponse {
        val requestBody = SetDefaultEngineVariantRequest(variant, version)

        return client.post("http://127.0.0.1:${this.port}/v1/engines/$name/default") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a DELETE request to the server to uninstall an engine based on engine, version, and variant.
     * If version and variant are not provided, all versions and variants of the engine will be uninstalled.
     * @param name: The name of the engine
     * @param variant: The variant of the engine (optional)
     * @param version: The version of the engine (optional)
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun uninstallEngine(name: String, variant: String? = null, version: String? = null): HttpResponse {
        val requestBody = UninstallEngineRequest(variant, version)

        return client.delete("http://127.0.0.1:${this.port}/v1/engines/$name/install") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a POST request to the server to install an engine of a specific type, with optional version and variant.
     * If none are provided, the latest version and most suitable variant will be installed.
     * @param name: The name of the engine
     * @param apiKey: The API key for authentication for remote engine
     * @param metadata: The metadata object containing get_models_url, type, and url
     * @param variant: The variant of the engine to install (optional)
     * @param version: The version of the engine to install (optional)
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun installEngine(
        name: String,
        apiKey: String,
        metadata: Map<String, String>,
        variant: String? = null,
        version: String? = null
    ): HttpResponse {
        val requestBody = InstallEngineRequest(
            api_key = apiKey,
            metadata = metadata,
            type = metadata["type"]!!,
            url = metadata["url"]!!,
            variant = variant,
            version = version
        )

        return client.post("http://127.0.0.1:${this.port}/v1/engines/$name/install") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a POST request to the server to load the specified engine type.
     * @param name: The name of the engine
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun loadEngine(name: String): HttpResponse {
        return client.post("http://127.0.0.1:${this.port}/v1/engines/$name/load") {
            contentType(ContentType.Text.Plain.withCharset(Charsets.UTF_8))
            setBody("")
        }
    }

    /**
     * This function sends a GET request to the server to list released engines of a specific engine type.
     * @param name: The name of the engine
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun listReleasedEngines(name: String): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/engines/$name/releases") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a GET request to the server to return variants for the latest engine release of a specific engine type.
     * @param name: The name of the engine
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun getLatestRelease(name: String): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/engines/$name/releases/latest") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a GET request to the server to list all the files in the current directory.
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun listFiles(): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/files") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a POST request to the server to upload a file.
     * @param filePath: The path to the file to be uploaded
     * @param purpose: The intended purpose of the uploaded file
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun uploadFile(filePath: String, purpose: String = "assistants"): HttpResponse {
        return client.submitFormWithBinaryData(
            url = "http://127.0.0.1:${this.port}/v1/files",
            formData = formData {
                append("file", File(filePath).readBytes(), Headers.build {
                    append(
                        HttpHeaders.ContentDisposition,
                        "form-data; name=\"file\"; filename=\"${File(filePath).name}\""
                    )
                })
                append("purpose", purpose)
            }
        )
    }

    /**
     * This function sends a DELETE request to the server to delete a file by its ID.
     * @param id: The ID of the file to delete
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun deleteFile(id: String): HttpResponse {
        return client.delete("http://127.0.0.1:${this.port}/v1/files/$id") {
            contentType(ContentType.Text.Plain.withCharset(Charsets.UTF_8))
            setBody("")
        }
    }


    /**
     * This function sends a GET request to the server to retrieve a file by its ID.
     * @param id: The ID of the file to retrieve
     * @param thread: The optional thread identifier
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun retrieveFile(id: String, thread: String? = null): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/files/$id") {
            contentType(ContentType.Application.Json)
            thread?.let { parameter("thread", it) }
        }
    }

    /**
     * This function sends a GET request to the server to retrieve the content of a file by its ID.
     * @param id: The ID of the file to retrieve content from
     * @param thread: The optional thread identifier
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun getFileContent(id: String, thread: String? = null): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/files/$id/content") {
            contentType(ContentType.Application.Json)
            thread?.let { parameter("thread", it) }
        }
    }

    /**
     * This function sends a GET request to the server to retrieve the hardware information
     * @return The HttpResponse object
     * @throws Exception if the request fails
     */
    suspend fun getHardwareInfo(): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/hardware") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a GET request to the server to retrieve the thread status of the server
     * @param metadata: The metadata object
     * @return The HttpResponse object
     * @throws Exception if the request fails
     */
    suspend fun createThread(metadata: Map<String, String>? = null): HttpResponse {
        val requestBody = Thread(id = "", metadata = metadata)
        return client.post("http://127.0.0.1:${this.port}/v1/threads") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a DELETE request to the server to delete a thread by its ID.
     * @param id: The ID of the thread to delete
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun deleteThread(id: String): HttpResponse {
        return client.delete("http://127.0.0.1:${this.port}/v1/threads/$id") {
            contentType(ContentType.Text.Plain.withCharset(Charsets.UTF_8))
            setBody("")
        }
    }

    /**
     * This function sends a GET request to the server to retrieve a thread by its ID.
     * @param id: The ID of the thread to retrieve
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun retrieveThread(id: String): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/threads/$id") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a PATCH request to the server to modify a thread by its ID.
     * @param id: The ID of the thread to modify
     * @param metadata: The metadata object
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun modifyThread(id: String, metadata: Map<String, String>): HttpResponse {
        val requestBody = Thread(id = id, metadata = metadata)
        return client.patch("http://127.0.0.1:${this.port}/v1/threads/$id") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a POST request to the server to create a new message in a thread.
     * @param threadId: The ID of the thread to create the message in
     * @param content: The content of the message
     * @param role: The role of the message sender (e.g., "user", "assistant")
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun createMessage(threadId: String, content: String, role: String): HttpResponse {
        val requestBody = CreateMessageRequest(content, role)

        return client.post("http://127.0.0.1:${this.port}/v1/threads/$threadId/messages") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a DELETE request to the server to delete a specific message from a thread.
     * @param threadId: The ID of the thread containing the message
     * @param messageId: The ID of the message to delete
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun deleteMessage(threadId: String, messageId: String): HttpResponse {
        return client.delete("http://127.0.0.1:${this.port}/v1/threads/$threadId/messages/$messageId") {
            contentType(ContentType.Text.Plain.withCharset(Charsets.UTF_8))
            setBody("")
        }
    }

    /**
     * This function sends a GET request to the server to list all messages in a thread.
     * @param threadId: The ID of the thread
     * @param limit: The maximum number of messages to return
     * @param order: The order of the messages
     * @param after: The message ID to start after
     * @param before: The message ID to start before
     * @param runId: The run ID
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun listMessages(
        threadId: String,
        limit: Int? = null,
        order: String? = null,
        after: String? = null,
        before: String? = null,
        runId: String? = null
    ): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/threads/$threadId/messages") {
            contentType(ContentType.Application.Json)
            limit?.let { parameter("limit", it) }
            order?.let { parameter("order", it) }
            after?.let { parameter("after", it) }
            before?.let { parameter("before", it) }
            runId?.let { parameter("run_id", it) }
        }
    }


    /**
     * This function sends a GET request to the server to retrieve a specific message from a thread by its ID.
     * @param threadId: The ID of the thread containing the message
     * @param messageId: The ID of the message to retrieve
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun retrieveMessage(threadId: String, messageId: String): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/threads/$threadId/messages/$messageId") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a PATCH request to the server to modify a specific message's content or metadata in a thread.
     * @param threadId: The ID of the thread containing the message
     * @param messageId: The ID of the message to modify
     * @param content: The new content for the message
     * @param metadata: The updated metadata for the message
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun modifyMessage(
        threadId: String,
        messageId: String,
        content: String,
        metadata: Map<String, String>
    ): HttpResponse {
        val requestBody = ModifyMessageRequest(content, metadata)

        return client.patch("http://127.0.0.1:${this.port}/v1/threads/$threadId/messages/$messageId") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a POST request to the server to add a new remote model configuration.
     * @param request: The apiObjects.model.AddRemoteModelRequest object containing the model configuration details
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun addRemoteModel(request: AddRemoteModelRequest): HttpResponse {
        return client.post("http://127.0.0.1:${this.port}/v1/models/add") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    /**
     * This function sends a POST request to the server to import a model from a specified path.
     * @param model: The unique identifier of the model
     * @param modelPath: The file path to the model
     * @param name: The display name of the model (optional)
     * @param option: The import option such as symlink or copy (optional)
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun importModel(
        model: String,
        modelPath: String,
        name: String? = null,
        option: String? = null
    ): HttpResponse {
        val requestBody = ImportModelRequest(model, modelPath, name, option)

        return client.post("http://127.0.0.1:${this.port}/v1/models/import") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a DELETE request to the server to stop the download of a model with the corresponding taskId.
     * @param taskId: The unique identifier of the download task to be stopped
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun stopModelDownload(taskId: String): HttpResponse {
        val requestBody = StopModelDownloadRequest(taskId)

        return client.delete("http://127.0.0.1:${this.port}/v1/models/pull") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }


    /**
     * This function sends a POST request to the server to pull a model from a remote source.
     * @param id: The id which will be used to register the model (optional)
     * @param model: The identifier or URL of the model to use
     * @param name: The name which will be used to overwrite the model name (optional)
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun pullModel(id: String? = null, model: String, name: String? = null): HttpResponse {
        val requestBody = PullModelRequest(id, model, name)

        return client.post("http://127.0.0.1:${this.port}/v1/models/pull") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a DELETE request to the server to remove a Huggingface Organization or Repository.
     * @param source: The URL of the model source to remove
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun removeModelSource(source: String): HttpResponse {
        val requestBody = RemoveModelSourceRequest(source)

        return client.delete("http://127.0.0.1:${this.port}/v1/models/sources") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a POST request to the server to add a Huggingface Organization or Repository.
     * @param source: The URL of the model source to add
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun addModelSource(source: String): HttpResponse {
        val requestBody = AddModelSourceRequest(source)

        return client.post("http://127.0.0.1:${this.port}/v1/models/sources") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a GET request to the server to list the currently available models.
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun listModels(): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/models") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a POST request to the server to load a model into memory.
     * @param cacheType: KV cache type (optional, default is "f16")
     * @param cachingEnabled: To enable prompt caching or not (optional, default is false)
     * @param ctxLen: The context length for model operations (optional)
     * @param mmproj: Local path to mmproj model file (optional)
     * @param model: The downloaded model name (required)
     * @param modelPath: Local path to LLM model file (optional)
     * @param nParallel: Number of parallel processing units to use (optional, default is 1)
     * @param ngl: Determines GPU layer usage (optional)
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun startModel(
        cacheType: String? = "f16",
        cachingEnabled: Boolean? = false,
        ctxLen: Int? = null,
        mmproj: String? = null,
        model: String,
        modelPath: String? = null,
        nParallel: Int? = 1,
        ngl: Int? = null
    ): HttpResponse {
        val requestBody = StartModelRequest(
            cache_type = cacheType,
            caching_enabled = cachingEnabled,
            ctx_len = ctxLen,
            mmproj = mmproj,
            model = model,
            model_path = modelPath,
            n_parallel = nParallel,
            ngl = ngl
        )

        return client.post("http://127.0.0.1:${this.port}/v1/models/start") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }

    }

    /**
     * This function sends a POST request to the server to unload a model from memory.
     * @param model: The downloaded model name (required)
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun stopModel(model: String): HttpResponse {
        val requestBody = StopModelRequest(model)

        return client.post("http://127.0.0.1:${this.port}/v1/models/stop") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }
    }

    /**
     * This function sends a DELETE request to the server to delete a model.
     * @param id: The unique identifier of the model to delete
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun deleteModel(id: String): HttpResponse {
        return client.delete("http://127.0.0.1:${this.port}/v1/models/$id") {
            contentType(ContentType.Text.Plain.withCharset(Charsets.UTF_8))
            setBody("")
        }
    }

    /**
     * This function sends a GET request to the server to retrieve a model instance.
     * @param id: The unique identifier of the model
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun getModel(id: String): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/models/$id") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a PATCH request to the server to update a model instance.
     * @param modelId: The unique identifier of the model to update
     * @param request: The apiObjects.model.UpdateModelRequest object containing the model details
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun updateModel(modelId: String, request: UpdateModelRequest): HttpResponse {
        return client.patch("http://127.0.0.1:${this.port}/v1/models/$modelId") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    /**
     * This function sends a GET request to the server to check the application's health status.
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun checkHealth(): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/healthz") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a DELETE request to the server to initiate the shutdown process.
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun terminateServer(): HttpResponse {
        return client.delete("http://127.0.0.1:${this.port}/processManager/destroy") {
            contentType(ContentType.Text.Plain.withCharset(Charsets.UTF_8))
            setBody("")
        }
    }


    /**
     * This function sends a GET request to the server to retrieve the current configuration settings.
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun getConfigurations(): HttpResponse {
        return client.get("http://127.0.0.1:${this.port}/v1/configs") {
            contentType(ContentType.Application.Json)
        }
    }

    /**
     * This function sends a PATCH request to the server to update the configuration settings.
     * @param request: The apiObjects.configuration.UpdateConfigurationSettingsRequest object containing the configuration settings
     * @return: The HttpResponse object
     * @throws: Exception if the request fails
     */
    suspend fun updateConfigurationSettings(request: UpdateConfigurationSettingsRequest): HttpResponse {
        return client.patch("http://127.0.0.1:${this.port}/v1/configs") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    fun stop() {
        stopCortexCpp()
    }
}
