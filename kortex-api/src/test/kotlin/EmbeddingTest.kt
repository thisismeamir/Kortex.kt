import io.github.thisismeamir.kortex.Kortex
import io.github.thisismeamir.kortex.objs.embedding.EmbeddingRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class EmbeddingTest {

    val kortex = Kortex()

    @Test
    fun `create embedding`(){
        runBlocking {
            val embedding = kortex.createEmbedding(embeddingRequest = EmbeddingRequest<String>(
                embeddingFormat = "float",
                input = "Hello",
                model = kortex.getAllModels().first().id
            ))

            println(embedding)
        }
    }
}