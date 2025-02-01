import com.thisismeamir.Kortex
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class Models {
    @Test
    fun `Get Models`() {
        val kortex = Kortex()
        runBlocking {
            println(kortex.getModels().size)
        }
    }

    @Test
    fun `Start Model`() {
        val kortex = Kortex()
        runBlocking {

        }
    }

    @Test
    fun `Stop Model`() {
        val kortex = Kortex()
        runBlocking {

        }
    }

    @Test
    fun `Delete Model`() {
        println("Be careful, testing this method will delete the model on your computer.")
        val kortex = Kortex()
        runBlocking {

        }
    }

    @Test
    fun `Get Model`() {
        val kortex = Kortex()
        runBlocking {

        }
    }

    @Test
    fun `Update Model`() {
        println("Be careful, testing this method will update the model on your computer.")
        val kortex = Kortex()
        runBlocking {

        }
    }
}