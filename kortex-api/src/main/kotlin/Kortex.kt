import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import utils.CortexLogLevel
import utils.executeCommand
import kotlin.properties.Delegates


class Kortex {

    var port by Delegates.notNull<Int>()
    lateinit var logLevel: String

    /** Starting the cortex.cpp application using terminal command execution.
     * If cortex.cpp is already initiated a server on your computer
     * you can skip this step and directly use the API class.
     * @param port: The port to start the server on
     * @param logLevel: The log level of the server
     * @return: Unit
     */
    constructor(port: Int, logLevel: CortexLogLevel) {
        executeCommand("cortex start -p $port --loglevel ${logLevel.name}")
        this.port = port
        this.logLevel = logLevel.name
    }

    /**
     * This is another constructor that simply does nothing.
     * This can be used when cortex is already running, and you don't want to start it again.
     * @return: Unit
     */
    constructor() {}

    /**
     * This is the main function that starts the server.
     * @param args: The arguments passed to the main function. (Right now does nothing)
     * @return: Unit
     */
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Kortex(5555, CortexLogLevel.DEBUG)
        }
    }

    /**
     * variable is a Ktor HTTP Client that is used to make requests to the cortex server.
     */
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { prettyPrint = true; ignoreUnknownKeys = true })
        }
    }
}