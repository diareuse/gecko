package gecko.android.example.generator

import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random.Default.nextBoolean

class GeneratorBody(
    private val array: Generator<JSONArray>,
    private val json: Generator<JSONObject>
) : Generator<String> {

    override fun generate() = when (nextBoolean()) {
        true -> array.generate().toString()
        false -> json.generate().toString()
    }

}