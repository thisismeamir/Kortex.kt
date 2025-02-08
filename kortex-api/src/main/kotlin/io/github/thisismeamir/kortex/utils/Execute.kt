package io.github.thisismeamir.kortex.utils
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Executes a given command in the system's runtime environment and returns the output as a string.
 *
 * @param command The command to be executed.
 * @return The output of the executed command.
 */
fun execute(command: String): String {
    val process = Runtime.getRuntime().exec(command)
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    val output = StringBuilder()
    var line: String?

    while (reader.readLine().also { line = it } != null) {
        output.append(line).append("\n")
    }

    reader.close()
    process.waitFor()
    return output.toString()
}