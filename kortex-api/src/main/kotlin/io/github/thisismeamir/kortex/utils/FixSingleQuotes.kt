package io.github.thisismeamir.kortex.utils

fun String.fixSingleQuotes(): String {
    return this.replace("'", "\"")
}