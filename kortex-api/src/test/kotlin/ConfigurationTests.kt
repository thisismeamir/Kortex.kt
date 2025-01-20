import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import utils.CortexLogLevel
import kotlin.test.BeforeTest

class ConfigurationTests {


    @Test
    fun `Configuration Status`(){
        val kortex = Kortex()
        runBlocking {
            println(kortex.getConfiguration())
        }

    }

    @Test
    fun `Update Configuration`() {
        val kortex = Kortex()
        runBlocking {
            val config = kortex.getConfiguration()
            val (status, body) = kortex.updateConfiguration(config)
            println("Status: $status, Body: $body")
        }

    }

    @Test
    fun `Check Health of Server`(){
        val kortex = Kortex()
        runBlocking {
            println(kortex.getServerHealth())
        }

    }

    @Test
    fun `Terminate Server`() {
        val kortex = Kortex()
        runBlocking {
            println(kortex.terminateServer())
        }
    }
}