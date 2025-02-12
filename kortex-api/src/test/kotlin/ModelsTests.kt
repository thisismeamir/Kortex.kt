import io.github.thisismeamir.kortex.Kortex
import io.github.thisismeamir.kortex.objs.startmodel.StartModelRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class ModelsTests {

    private val kortex = Kortex()

    @Test
    fun `get all models`() {
        runBlocking {
            kortex.getAllModels().forEach { println(it.name) }
        }
    }

    @Test
    fun `get single model`() {
        runBlocking {
            println(kortex.getModel(kortex.getAllModels().first().id).name)
        }
    }

    @Test
    fun `start model`() {
        runBlocking {
            println(kortex.startModel(StartModelRequest(kortex.getAllModels().last().id)).second)
        }
    }

    @Test
    fun `stop model`() {
        runBlocking {
            println(kortex.stopModel(kortex.getAllModels().last().id).second)
        }
    }

    @Test
    fun `delete model`() {
        runBlocking {
            // Test is dangerous
        }
    }

    @Test
    fun `update model`() {
        runBlocking {
//           kortex.updateModel(kortex.getAllModels().last().id, UpdateModelRequest(
//
//           ))
        }
    }

    @Test
    fun `add remote model`() {
        // Need someone with access to remote models to actually make this one test
    }

    @Test
    fun `import model`() {
        runBlocking {
        }
    }

    @Test
    fun `pull model`() {
        // Pulling a model
    }

    @Test
    fun `stop model download`(){

    }

    @Test
    fun `remove model source`(){}

    @Test
    fun `add model source`() {}

}