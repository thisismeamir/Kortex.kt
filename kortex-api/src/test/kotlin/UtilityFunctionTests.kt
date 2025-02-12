import io.github.thisismeamir.kortex.utils.snakeToCamelCase
import org.junit.jupiter.api.Test

class UtilityFunctionTests {

    @Test
    fun `Snake to Camel Case`(){
            val jsonString = """
        {
            "some_text_with_underline": "value",
            "nested_object": {
                "another_key": "another_value"
            }
        }
    """
        val result = jsonString.snakeToCamelCase()
        println(result)

    }
}