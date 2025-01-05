import io.ktor.client.*
import kotlinx.coroutines.coroutineScope
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
    }

    @Test
    suspend fun setup() {
        kortex = Kortex()
        coroutineScope {
            kortex.getConfigurations()
        }
    }
}