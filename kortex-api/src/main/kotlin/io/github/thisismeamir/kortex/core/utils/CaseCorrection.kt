package io.github.thisismeamir.kortex.core.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

fun String.snakeToCamelCase(): String {
    val jsonElement = Json.parseToJsonElement(this)
    val transformedElement = transformKeys(jsonElement)
    return Json.encodeToString(transformedElement)
}

fun transformKeys(element: JsonElement): JsonElement {
    return when (element) {
        is JsonObject -> {
            val transformedMap = element.mapKeys { (key, _) -> key.toCamelCase() }
                .mapValues { (_, value) -> transformKeys(value) }
            JsonObject(transformedMap)
        }
        is JsonArray -> JsonArray(element.map { transformKeys(it) })
        else -> element
    }
}

fun String.toCamelCase(): String {
    return split('_').joinToString("") { it.capitalize() }.decapitalize()
}

