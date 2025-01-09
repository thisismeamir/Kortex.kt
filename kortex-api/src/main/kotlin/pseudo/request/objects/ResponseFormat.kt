package pseudo.request.objects

/**
 * An object specifying the format that the model must output.
 * Setting to { "type": "json_object" } enables JSON mode,
 * which guarantees the message the model generates is valid JSON.
 * @property TEXT
 * @property JSONOBJECT
 * @property JSONSCHEMA
 */
enum class ResponseFormat {
    TEXT,
    JSONOBJECT,
    JSONSCHEMA
}