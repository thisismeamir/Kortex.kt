import com.thisismeamir.Kortex
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import com.thisismeamir.objs.engine.InstallEngine
import com.thisismeamir.objs.engine.VariantRequestBody
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
        println(engines)
//        assertEquals(2, engines.size)
    }

    @Test
    fun testGetDefaultEngine() = runBlocking {
        val engine = kortex.getDefaultEngine("llama-cpp")
        println(engine)
//        assertEquals("linux-amd64-avx2-cuda-12-0", engine.name)
    }

    @Test
    fun testSetDefaultEngineVariant() = runBlocking {
        val engine = kortex.getInstalledEngines("llama-cpp")
        println(engine)
        val response = kortex.setDefaultEngineVariant("llama-cpp", VariantRequestBody(engine[1].name, engine[1].version))
        assertEquals("Engine linux-amd64-avx2-cuda-12-0 0.1.49 set as default", response.message)
    }

    @Test
    fun testUninstallEngine() = runBlocking {
        val engine = kortex.getDefaultEngine("llama-cpp")
        val response = kortex.uninstallEngine("llama-cpp", VariantRequestBody(engine.engine, engine.version))
        println(response.message)
//        assertEquals("Engine llama-cpp uninstalled successfully!", response.message)
    }

    @Test
    fun testInstallEngine() = runBlocking {
        val engine = kortex.installEngine("llama-cpp", InstallEngine(
            apiKey = "",
            type = "local",
            url = "https://api.openai.com",
            variant ="linux-amd64-avx2-cuda-12-0 0.1.49",
            version = "v0.1.39",
            metadata = mapOf("getModelsUrl" to "https://api.openai.com/v1/models")
        )
        )
        assertEquals("Engine starts installing!", engine.message)
    }

    @Test
    fun testLoadEngine() = runBlocking {
        val response = kortex.loadEngine("llama-cpp", )
        println(response.message)
    }

    @Test
    fun testUnloadEngine() = runBlocking {
        val response = kortex.unloadEngine("llama-cpp")
        println(response.message)
    }

    @Test
    fun testGetReleasedEngines() = runBlocking {
        val engines = kortex.getReleasedEngines("llama-cpp")
        println(engines)
    }

    @Test
    fun testGetLatestEngineRelease() = runBlocking {
        val engines = kortex.getLatestEngineRelease("llama-cpp")
        println(engines)
    }

    @Test
    fun testUpdateEngine() = runBlocking {
        val response = kortex.updateEngine("llama-cpp")
        println(response.message)
    }
}