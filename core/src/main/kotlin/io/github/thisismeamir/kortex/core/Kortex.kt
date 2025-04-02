package io.github.thisismeamir.kortex.core

import io.github.thisismeamir.kortex.core.objs.Configuration
import io.github.thisismeamir.kortex.core.objs.DataListResponse
import io.github.thisismeamir.kortex.core.objs.DeleteObjectResponse
import io.github.thisismeamir.kortex.core.objs.MessageResponse
import io.github.thisismeamir.kortex.core.objs.importmodel.ImportModelRequest
import io.github.thisismeamir.kortex.core.objs.addremotemodel.AddRemoteModelRequest
import io.github.thisismeamir.kortex.core.objs.completions.ChatCompletionResponse
import io.github.thisismeamir.kortex.core.objs.addremotemodel.AddRemoteModelResponse
import io.github.thisismeamir.kortex.core.objs.addremotemodel.StopModelDownloadResponse
import io.github.thisismeamir.kortex.core.objs.chat.messages.CreateMessageRequestBody
import io.github.thisismeamir.kortex.core.objs.chat.messages.CreateMessageResponse
import io.github.thisismeamir.kortex.core.objs.chat.messages.ListMessagesQueryParameters
import io.github.thisismeamir.kortex.core.objs.chat.threads.MessageThread
import io.github.thisismeamir.kortex.core.objs.chat.threads.UpdateMetaDataRequest
import io.github.thisismeamir.kortex.core.objs.completions.ChatCompletionRequest
import io.github.thisismeamir.kortex.core.objs.embedding.Embedding
import io.github.thisismeamir.kortex.core.objs.embedding.EmbeddingRequest
import io.github.thisismeamir.kortex.core.objs.engine.Engine
import io.github.thisismeamir.kortex.core.objs.engine.InstallEngine
import io.github.thisismeamir.kortex.core.objs.engine.LatestReleasedEngine
import io.github.thisismeamir.kortex.core.objs.engine.ReleasedEngine
import io.github.thisismeamir.kortex.core.objs.file.File
import io.github.thisismeamir.kortex.core.objs.engine.VariantRequestBody
import io.github.thisismeamir.kortex.core.objs.getmodel.GetModelsResponse
import io.github.thisismeamir.kortex.core.objs.getmodel.Model
import io.github.thisismeamir.kortex.core.objs.hardware.ActivateGpusRequest
import io.github.thisismeamir.kortex.core.objs.hardware.ActivateGpusResponse
import io.github.thisismeamir.kortex.core.objs.importmodel.ImportModelResponse
import io.github.thisismeamir.kortex.core.objs.pullmodel.PullModelRequest
import io.github.thisismeamir.kortex.core.objs.pullmodel.PullModelResponse
import io.github.thisismeamir.kortex.core.objs.removemodel.RemoveModelSourceRequest
import io.github.thisismeamir.kortex.core.objs.startmodel.StartModelRequest
import io.github.thisismeamir.kortex.core.objs.updatemodel.UpdateModelRequest
import io.github.thisismeamir.kortex.core.objs.updatemodel.UpdateModelResponse
import io.github.thisismeamir.kortex.core.utils.execute
import io.github.thisismeamir.kortex.core.utils.fixSingleQuotes
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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import java.nio.file.Path


class Kortex(private val baseUrl: String = "http://127.0.0.1:39281") {

    init {
        execute("cortex start")
    }

    var runningModel: String = ""

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        namingStrategy = JsonNamingStrategy.SnakeCase
    }

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }

    suspend fun getConfiguration(): Configuration =
        json.decodeFromString(client.get("$baseUrl/v1/configs").bodyAsText().fixSingleQuotes())

    suspend fun updateConfiguration(config: Configuration): Pair<Int, String> =
        client.post("$baseUrl/v1/configs") {
            contentType(ContentType.Application.Json)
            setBody(config)
        }.let { it.status.value to it.bodyAsText() }

    suspend fun getServerHealth(): Int =
        client.get("$baseUrl/healthz").status.value

    suspend fun terminateServer(): Int =
        client.delete("$baseUrl/processManager/destroy").status.value

    suspend fun getAllModels(): List<Model> =
        json.decodeFromString<GetModelsResponse>(client.get("$baseUrl/v1/models").bodyAsText().fixSingleQuotes()).data

    suspend fun startModel(model: StartModelRequest): Pair<Int, String> {
        val response = client.post("$baseUrl/v1/models/start") {
            contentType(ContentType.Application.Json)
            setBody(model)
        }.let { it.status.value to it.bodyAsText() }
        if (response.first == 200) {
            runningModel = model.model
        }
        return response
    }

    suspend fun stopModel(model: String): Pair<Int, String> {
       val response = client.post("$baseUrl/v1/models/stop") {
            contentType(ContentType.Application.Json)
            setBody("{\"model\": \"$model\"}")
        }.let { it.status.value to it.bodyAsText() }
        if (runningModel == model) {
            runningModel = ""
        }
        return response
    }


    suspend fun deleteModel(model: String): DeleteObjectResponse =
        json.decodeFromString(client.delete("$baseUrl/v1/models/$model").bodyAsText().fixSingleQuotes())

    suspend fun getModel(model: String): Model =
        json.decodeFromString(client.get("$baseUrl/v1/models/$model").bodyAsText().fixSingleQuotes())

    suspend fun updateModel(model: String, updateRequest: UpdateModelRequest): UpdateModelResponse =
        json.decodeFromString(client.patch("$baseUrl/v1/models/$model") {
            contentType(ContentType.Application.Json)
            setBody(updateRequest)
        }.bodyAsText().fixSingleQuotes())

    suspend fun addRemoteModel(request: AddRemoteModelRequest): AddRemoteModelResponse =
        json.decodeFromString(client.post("$baseUrl/v1/models/add") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyAsText().fixSingleQuotes())

    suspend fun importModel(request: ImportModelRequest): ImportModelResponse =
        json.decodeFromString(client.post("$baseUrl/v1/models/import") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyAsText().fixSingleQuotes())

    suspend fun stopModelDownload(taskId: String): StopModelDownloadResponse =
        json.decodeFromString(client.delete("$baseUrl/v1/models/pull") {
            contentType(ContentType.Application.Json)
            setBody("{\"taskId\": \"$taskId\"}")
        }.bodyAsText().fixSingleQuotes())

    suspend fun pullModel(request: PullModelRequest): PullModelResponse =
        json.decodeFromString(client.post("$baseUrl/v1/models/pull") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyAsText().fixSingleQuotes())

    suspend fun removeModelSource(request: RemoveModelSourceRequest): MessageResponse =
        json.decodeFromString(client.delete("$baseUrl/v1/models/sources") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyAsText().fixSingleQuotes())

    suspend fun addModelSource(sourcePath: String): MessageResponse =
        json.decodeFromString(client.post("$baseUrl/v1/models/") {
            contentType(ContentType.Application.Json)
            setBody("{\"source\": \"$sourcePath\"}")
        }.bodyAsText().fixSingleQuotes())

    suspend fun getThreads(): List<MessageThread> =
        json.decodeFromString<DataListResponse<MessageThread>>(
            client.get("$baseUrl/v1/threads").bodyAsText().fixSingleQuotes()
        ).data

    suspend fun createThread(title: String): MessageThread =
        json.decodeFromString(client.post("$baseUrl/v1/threads") {
            contentType(ContentType.Application.Json)
            setBody("{\"metadata\": {\"title\": \"$title\"}}")
        }.bodyAsText())

    suspend fun deleteThread(threadId: String): DeleteObjectResponse =
        json.decodeFromString(client.delete("$baseUrl/v1/threads/$threadId").bodyAsText().fixSingleQuotes())

    suspend fun getThread(threadId: String): MessageThread =
        json.decodeFromString(client.get("$baseUrl/v1/threads/$threadId").bodyAsText().fixSingleQuotes())

    suspend fun updateThreadMetadata(threadId: String, request: UpdateMetaDataRequest): String =
        TODO()
        // As soon as the bug be fixed at the server side code.
        //        json.decodeFromString(client.patch("$baseUrl/v1/threads/$threadId") {
        //            contentType(ContentType.Application.Json)
        //            setBody(request)
        //        }.bodyAsText().fixSingleQuotes())

    suspend fun getMessages(threadId: String, queryParameters: ListMessagesQueryParameters): List<CreateMessageResponse> =
        json.decodeFromString<DataListResponse<CreateMessageResponse>>(
            client.get("$baseUrl/v1/threads/$threadId/messages") {
            url {
                queryParameters.before?.let { it1 -> parameters.append("before", it1) }
                queryParameters.after?.let { it1 -> parameters.append("after", it1) }
                queryParameters.runId?.let { it1 -> parameters.append("runId", it1) }
                queryParameters.order?.let { it1 -> parameters.append("order", it1) }
                parameters.append("limit", queryParameters.limit.toString())
            }
        }.bodyAsText().fixSingleQuotes()
    ).data

    suspend fun createMessage(threadId: String, content: CreateMessageRequestBody): CreateMessageResponse =
        json.decodeFromString(
            client.post("$baseUrl/v1/threads/$threadId/messages") {
            contentType(ContentType.Application.Json)
            setBody(content)
        }.bodyAsText()
        )

    suspend fun deleteMessage(threadId: String, messageId: String): DeleteObjectResponse =
        json.decodeFromString(client.delete("$baseUrl/v1/threads/$threadId/messages/$messageId").bodyAsText())

    suspend fun retrieveMessage(threadId: String, messageId: String): CreateMessageResponse =
        json.decodeFromString(
            client.get("$baseUrl/v1/threads/$threadId/messages/$messageId").bodyAsText()
    )

    suspend fun modifyMessageMetadata(threadId: String, messageId: String, metadata: String): CreateMessageResponse =
        json.decodeFromString(client.patch("$baseUrl/v1/threads/$threadId/messages/$messageId") {
            contentType(ContentType.Application.Json)
            setBody("{\"metadata\": {$metadata} }")
        }.bodyAsText())

    suspend fun getHardware(): String =
//        json.decodeFromString(
            client.get("$baseUrl/v1/hardware").bodyAsText()
//        )

    suspend fun activateGpus(gpus: ActivateGpusRequest): ActivateGpusResponse =
        json.decodeFromString(client.post("$baseUrl/v1/hardware/activate") {
            contentType(ContentType.Application.Json)
            setBody(gpus)
        }.bodyAsText())

    suspend fun getFiles(): List<File> =
        json.decodeFromString<DataListResponse<File>>(client.get("$baseUrl/v1/files").bodyAsText()).data

    suspend fun uploadFile(filePath: String, purpose: String): File =
        json.decodeFromString(
            client.post("$baseUrl/v1/files") {
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
        }.bodyAsText()
    )

    suspend fun deleteFile(fileId: String): DeleteObjectResponse =
        json.decodeFromString(client.delete("$baseUrl/v1/files/$fileId").bodyAsText())

    suspend fun retrieveFile(fileId: String): File =
        json.decodeFromString(client.get("$baseUrl/v1/files/$fileId").bodyAsText())

    suspend fun getFileContent(fileId: String, savePath: Path, thread: String? = null) {
        val response: HttpResponse = client.get("$baseUrl/v1/files/$fileId/content") {
            thread?.let {
                url {
                    parameters.append("thread", it.toString())
                }
            }
        }
        java.io.File(savePath.toString()).writeBytes(response.body())
    }

    suspend fun getInstalledEngines(engineName: String): List<Engine> =
        json.decodeFromString(client.get("$baseUrl/v1/engines/$engineName").bodyAsText())

    suspend fun getDefaultEngine(engineName: String): Engine =
        json.decodeFromString(client.get("$baseUrl/v1/engines/$engineName/default").bodyAsText().replace("variant", "name"))
//        client.get("$baseUrl/v1/engines/$engineName/default").bodyAsText()

    suspend fun setDefaultEngineVariant(engineName: String, variant: VariantRequestBody): MessageResponse =
        json.decodeFromString(client.post("$baseUrl/v1/engines/$engineName/default") {
            contentType(ContentType.Application.Json)
            setBody(variant)
        }.bodyAsText())

    suspend fun uninstallEngine(engineName: String, variant: VariantRequestBody): MessageResponse =
        json.decodeFromString(client.delete("$baseUrl/v1/engines/$engineName/install") {
            contentType(ContentType.Application.Json)
            setBody(variant)
        }.bodyAsText())

    suspend fun installEngine(engineName: String, specification: InstallEngine): MessageResponse =
        json.decodeFromString(client.post("$baseUrl/v1/engines/$engineName/install") {
            contentType(ContentType.Application.Json)
            setBody(specification)
        }.bodyAsText())

    suspend fun unloadEngine(engineName: String): MessageResponse =
        json.decodeFromString(client.delete("$baseUrl/v1/engines/$engineName/load").bodyAsText())

    suspend fun loadEngine(engineName: String): MessageResponse =
        json.decodeFromString(client.post("$baseUrl/v1/engines/$engineName/load").bodyAsText())

    suspend fun getReleasedEngines(engineName: String): List<ReleasedEngine> =
        json.decodeFromString(client.get("$baseUrl/v1/engines/$engineName/releases").bodyAsText())

    suspend fun getLatestEngineRelease(engineName: String): List<LatestReleasedEngine> =
        json.decodeFromString(client.get("$baseUrl/v1/engines/$engineName/releases/latest").bodyAsText())
//        client.get("$baseUrl/v1/engines/$engineName/releases/latest").bodyAsText()

    suspend fun updateEngine(engineName: String): MessageResponse =
        json.decodeFromString(client.post("$baseUrl/v1/engines/$engineName/update").bodyAsText())

    suspend fun <T>createEmbedding(embeddingRequest: EmbeddingRequest<T>): List<Embedding> =
        json.decodeFromString(client.post("$baseUrl/embeddings") {
            contentType(ContentType.Application.Json)
            setBody(embeddingRequest)
        }.bodyAsText())

    suspend fun createChatCompletion(request: ChatCompletionRequest): ChatCompletionResponse =
        json.decodeFromString(client.post("$baseUrl/v1/chat/completions") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.bodyAsText())
}