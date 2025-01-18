import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import utils.CortexLogLevel
import utils.startCortexCpp


class ApiTests {

    private lateinit var kortex: Kortex
    private lateinit var client: HttpClient
    private val port = 55555

    @BeforeTest
    fun setupCortex() {
        startCortexCpp(port, CortexLogLevel.TRACE)
        client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json { prettyPrint = true; ignoreUnknownKeys = true })
            }
        }
        kortex = Kortex(port, CortexLogLevel.TRACE)
    }

    @Test
    fun testChatCompletion() = runBlocking {
    }
}