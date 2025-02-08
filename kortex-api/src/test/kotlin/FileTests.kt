import io.github.thisismeamir.kortex.Kortex
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertEquals

class FileTests {

    private val mockEngine = MockEngine { request ->
        respond(
            content = """{"message": "success"}""",
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        )
    }

    private val kortex = Kortex()

    @Test
    fun testUploadFile() = runBlocking {
        val file = kortex.uploadFile(
            "/home/kid-a/Documents/Projects/Active/kortex.cpp/kortex-api/src/main/resources/Discontinuous Galerkin Method.md",
            "assistants"
        )
        println(file)
        // Add assertions based on expected response
    }

    @Test
    fun testGetFiles() = runBlocking {
        val files = kortex.getFiles()
        println(files)
        // Add assertions based on expected response
    }


    @Test
    fun testDeleteFile() = runBlocking {
        val response = kortex.deleteFile(kortex.getFiles().first().id)
        println(response.deleted)
        // Add assertions based on expected response
    }

    @Test
    fun testRetrieveFile() = runBlocking {
        val file = kortex.retrieveFile(kortex.getFiles().first().id)
        println(file)
        // Add assertions based on expected response
    }

    @Test
    fun testGetFileContent() = runBlocking {
        kortex.getFileContent(kortex.getFiles().first().id, Path.of("/home/kid-a/Documents/Projects/Active/kortex.cpp/kortex-api/src/main/resources/new.md"))
        // Add assertions based on expected response
    }
}