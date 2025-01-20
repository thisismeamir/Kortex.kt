package utils

import kotlinx.serialization.json.*
import kotlinx.serialization.encodeToString

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

