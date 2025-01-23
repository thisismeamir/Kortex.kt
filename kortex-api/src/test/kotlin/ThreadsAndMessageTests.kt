import kotlinx.coroutines.runBlocking
import objs.chat.messages.*
import objs.chat.threads.*
import org.junit.jupiter.api.Test
import java.util.UUID

class ThreadsAndMessageTests {
    @Test
    fun `Get Threads`() {
        val kortex = Kortex()
        runBlocking {
            println(kortex.getThreads().first().id)
        }
    }

    @Test
    fun `Create Thread`() {
        val kortex = Kortex()
        runBlocking {
            val title = "New Thread ${UUID.randomUUID()}"
            println(kortex.createThread(title))
        }
    }

    @Test
    fun `Delete Thread`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            println(kortex.deleteThread(threadId))
        }
    }

    @Test
    fun `Get Thread`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            println(kortex.getThread(threadId))
        }
    }

    @Test
    fun `Update Thread Metadata`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            val updateRequest = UpdateMetaDataRequest(
                metadata = ThreadMetadata(title = "Hello", lastMessage = "No message")
            )
            println(kortex.updateThreadMetadata(threadId, updateRequest))
        }
    }

    @Test
    fun `Get Messages`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            val queryParameters = ListMessagesQueryParameters()
            println(kortex.getMessages(threadId, queryParameters).first().content)
        }
    }

    @Test
    fun `Create Message`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            val content = CreateMessageRequestBody(content = "New Message", role = "user")
            println(kortex.createMessage(threadId, content).status)
        }
    }

    @Test
    fun `Delete Message`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            val messageId = kortex.getMessages(threadId, ListMessagesQueryParameters(
                limit = 1
            )).first().id
            println(kortex.deleteMessage(threadId, messageId))
        }
    }

    @Test
    fun `Retrieve Message`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            val messageId = kortex.getMessages(threadId, ListMessagesQueryParameters()).first().id
            println(kortex.retrieveMessage(threadId, messageId).status)
        }
    }

    @Test
    fun `Modify Message Metadata`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            val messageId = kortex.getMessages(threadId, ListMessagesQueryParameters()).first().id
            val metadata = "{\"key\": \"value\"}"
            println(kortex.modifyMessageMetadata(threadId, messageId, metadata).status)
        }
    }
}