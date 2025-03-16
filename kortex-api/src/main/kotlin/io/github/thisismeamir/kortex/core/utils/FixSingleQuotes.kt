package io.github.thisismeamir.kortex.core.utils

fun String.fixSingleQuotes(): String {
    return this.replace("'", "\"")
}