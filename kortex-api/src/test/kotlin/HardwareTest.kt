import io.github.thisismeamir.kortex.Kortex
import io.github.thisismeamir.kortex.objs.hardware.ActivateGpusRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class HardwareTest {

    private val kortex = Kortex()

    @Test
    fun `get Hardware`() {
        runBlocking {
            println(kortex.getHardware())
        }
    }

    @Test
    fun `activate GPUs`() {
        runBlocking {
            println(kortex.activateGpus(ActivateGpusRequest(
                listOf(0)
            )))
        }
    }
}