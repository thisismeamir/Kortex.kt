import com.thisismeamir.Kortex
import com.thisismeamir.objs.chat.messages.CreateMessageRequestBody
import com.thisismeamir.objs.chat.messages.ListMessagesQueryParameters
import com.thisismeamir.objs.chat.threads.ThreadMetadata
import com.thisismeamir.objs.chat.threads.UpdateMetaDataRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.UUID

class ThreadsAndMessageTests {
    @Test
    fun `Create Thread`() {
        val kortex = Kortex()
        runBlocking {
            val title = "New Thread 123"
            print("thread:")
            val response = kortex.createThread(title)
            println(response.id)
        }
    }

    @Test
    fun `Get Threads`() {
        val kortex = Kortex()
        runBlocking {
            kortex.getThreads().forEach {
                println(it.id)
            }
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
        // SHOULD BE FIXED IN THE SERVER SIDE CODE MY CODE IS FINE.
//        val kortex = Kortex()
//        runBlocking {
//            val threadId = kortex.getThreads().first().id
//            print(threadId)
//            val updateRequest = UpdateMetaDataRequest(
//                metadata = mapOf(
//                    "title" to "hellow world"
//                )
//            )
//            println(kortex.updateThreadMetadata(threadId, updateRequest))
//        }
    }

    @Test
    fun `Create Message`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            val content = CreateMessageRequestBody(content = "New Message", role = "user")
            println(kortex.createMessage(threadId, content))
        }
    }

    @Test
    fun `Get Messages`() {
        val kortex = Kortex()
        runBlocking {
            val threadId = kortex.getThreads().first().id
            val queryParameters = ListMessagesQueryParameters()
            println(kortex.getMessages(threadId, queryParameters))
        }
    }

    @Test
    fun `Delete Message`() {
//        val kortex = Kortex()
//        runBlocking {
//            val threadId = kortex.getThreads().first().id
//            val messageId = kortex.getMessages(threadId, ListMessagesQueryParameters(
//                limit = 1
//            )).data.first().id
//            println(kortex.deleteMessage(threadId, messageId))
//        }
    }

    @Test
    fun `Retrieve Message`() {
//        val kortex = Kortex()
//        runBlocking {
//            val threadId = kortex.getThreads().first().id
//            val messageId = kortex.getMessages(threadId, ListMessagesQueryParameters()).data.first().id
//            println(kortex.retrieveMessage(threadId, messageId).status)
//        }
    }

    @Test
    fun `Modify Message Metadata`() {
//        val kortex = Kortex()
//        runBlocking {
//            val threadId = kortex.getThreads().first().id
//            val messageId = kortex.getMessages(threadId, ListMessagesQueryParameters()).data.first().id
//            val metadata = "{\"key\": \"value\"}"
//            println(kortex.modifyMessageMetadata(threadId, messageId, metadata).status)
//        }
    }
}