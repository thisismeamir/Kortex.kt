package utils

fun String.fixSingleQuotes(): String {
    return this.replace("'", "\"")
}