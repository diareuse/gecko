package gecko.android.example.generator

import org.json.JSONObject
import kotlin.random.Random
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt

internal class GeneratorJsonObject(
    private val string: Generator<String>,
    private val minCount: Int = 1,
    private val maxCount: Int = 5
) : Generator<JSONObject> {

    override fun generate(): JSONObject {
        val json = JSONObject()
        repeat(nextInt(minCount, maxCount)) {
            when (nextInt(0, 5)) {
                0 -> json.put(
                    string.generate(),
                    GeneratorJsonObject(string, maxCount = 2).generate()
                )
                1 -> json.put(string.generate(), nextInt())
                2 -> json.put(string.generate(), Random.nextDouble())
                3 -> json.put(string.generate(), nextBoolean())
                4 -> json.put(string.generate(), string.generate())
            }
        }
        return json
    }

}