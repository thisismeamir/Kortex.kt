package com.thisismeamir.utils

fun String.fixSingleQuotes(): String {
    return this.replace("'", "\"")
}