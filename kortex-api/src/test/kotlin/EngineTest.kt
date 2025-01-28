import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import objs.engine.InstallEngine
import objs.engine.VariantRequestBody
import kotlin.test.Test
import kotlin.test.assertEquals

class EngineTest {

    private val mockEngine = MockEngine { request ->
        respond(
            content = """{"message": "success"}""",
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        )
    }



    private val kortex = Kortex()

    @Test
    fun testGetInstalledEngines() = runBlocking {
        val engines = kortex.getInstalledEngines("llama-cpp")
        assertEquals(1, engines.size)
    }

    @Test
    fun testGetDefaultEngine() = runBlocking {
        val engine = kortex.getDefaultEngine("llama-cpp")
        assertEquals("llama-cpp", engine.name)
    }

    @Test
    fun testSetDefaultEngineVariant() = runBlocking {
        val engine = kortex.getDefaultEngine("llama-cpp")
        val response = kortex.setDefaultEngineVariant("llama-cpp", VariantRequestBody(engine.engine, engine.version))
        assertEquals("success", response.message)
    }

    @Test
    fun testUninstallEngine() = runBlocking {
        val engine = kortex.getDefaultEngine("llama-cpp")
        val response = kortex.uninstallEngine("llama-cpp", VariantRequestBody(engine.engine, engine.version))
        assertEquals("success", response.message)
    }

    @Test
    fun testInstallEngine() = runBlocking {

    }

    @Test
    fun testUnloadEngine() = runBlocking {
        val response = kortex.unloadEngine("llama-cpp")
        assertEquals("success", response.message)
    }

    @Test
    fun testLoadEngine() = runBlocking {
        val response = kortex.loadEngine("llama-cpp")
        assertEquals("success", response.message)
    }

    @Test
    fun testGetReleasedEngines() = runBlocking {
        val engines = kortex.getReleasedEngines("llama-cpp")
        assertEquals(1, engines.size)
    }

    @Test
    fun testGetLatestEngineRelease() = runBlocking {
        val engines = kortex.getLatestEngineRelease("llama-cpp")
        assertEquals(1, engines.size)
    }

    @Test
    fun testUpdateEngine() = runBlocking {
        val response = kortex.updateEngine("llama-cpp")
        assertEquals("success", response.message)
    }
}