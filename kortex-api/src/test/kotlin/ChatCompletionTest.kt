import io.github.thisismeamir.kortex.Kortex
import io.github.thisismeamir.kortex.objs.completions.ChatCompletionRequest
import io.github.thisismeamir.kortex.objs.completions.ChatCompletionRequestMessage
import io.github.thisismeamir.kortex.objs.startmodel.StartModelRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class ChatCompletionTest {

    val kortex = Kortex()

    @Test
    fun `Simple Chat Completion`() {
        runBlocking {
            val request = ChatCompletionRequest(
                messages = listOf(
                    ChatCompletionRequestMessage(
                        role = "user",
                        content = "Hello, how are you?",
                        name = "Kid A"
                    )
                ),
                model = kortex.getAllModels().last().id,
            )
            val engine = kortex.loadEngine("llama-cpp")
            val model = kortex.startModel(StartModelRequest(
                model = kortex.getAllModels().last().id
            ))
            val response = kortex.createChatCompletion(request)
            println(
                response.choices.first().message.content
            )
        }
    }
}