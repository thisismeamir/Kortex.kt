import kotlinx.coroutines.runBlocking
import objs.startmodel.StartModelRequest
import objs.updatemodel.UpdateModelRequest
import org.junit.jupiter.api.Test

class Models {
    @Test
    fun `Get Models`() {
        val kortex = Kortex()
        runBlocking {
            println(kortex.getModels().data.first().model)
        }
    }

    @Test
    fun `Start Model`() {
        val kortex = Kortex()
        runBlocking {
            val modelId = kortex.getModels().data.first().id
            println(kortex.startModel(StartModelRequest(modelId)))
        }
    }

    @Test
    fun `Stop Model`() {
        val kortex = Kortex()
        runBlocking {
            val modelId = kortex.getModels().data.first().id
            println(kortex.stopModel(modelId))
        }
    }

    @Test
    fun `Delete Model` () {
        println("Be careful, testing this method will delete the model on your computer.")
//        val kortex = Kortex()
//        runBlocking {
//            val modelId = kortex.getModels().data.first().id
//            println(kortex.deleteModel(modelId))
//        }
    }

    @Test
    fun `Get Model`() {
        val kortex = Kortex()
        runBlocking {
            val modelId = kortex.getModels().data.first().id
            println(kortex.getModel(modelId).model)
        }
    }

    @Test
    fun `Update Model`() {
        println("Be careful, testing this method will update the model on your computer.")
        println("To be done.")
    }
}