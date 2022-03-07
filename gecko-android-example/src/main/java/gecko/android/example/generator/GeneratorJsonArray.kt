package gecko.android.example.generator

import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random.Default.nextInt

class GeneratorJsonArray(
    private val json: Generator<JSONObject>,
    private val minCount: Int = 1,
    private val maxCount: Int = 5
) : Generator<JSONArray> {

    override fun generate(): JSONArray {
        val array = JSONArray()
        repeat(nextInt(minCount, maxCount)) {
            array.put(json.generate())
        }
        return array
    }

}