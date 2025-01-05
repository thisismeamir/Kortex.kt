package utils

/*
    This function serves as the starting point for Cortex.cpp,
    Since it's good to have a way to start the server from the codebase,
    without relying on terminal and the user.

    Other than start the server, it also sets the port and log level.
    but every other interaction would be done by the API class.
 */
fun startCortexCpp(port: Int, logLevel: CortexLogLevel) {
    executeCommand("cortex start -p $port --loglevel ${logLevel.name}")
}

