package utils


/**
   * This function executes a command in the terminal.
   * It's very important to make sure that the command is safe to execute.
   * @param command: The command to be executed
   * @return: The output of the command
 */
fun executeCommand(command: String) {
    val process = ProcessBuilder(command.split(" "))
        .redirectErrorStream(true)
        .start()
    process.inputStream.bufferedReader().use { println(it.readText()) }
    process.waitFor()
}