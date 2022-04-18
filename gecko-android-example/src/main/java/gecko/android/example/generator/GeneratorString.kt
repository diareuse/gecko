package gecko.android.example.generator

import kotlin.random.Random.Default.nextInt

internal class GeneratorString(
    private val minLength: Int = 5,
    private val maxLength: Int = 20
) : Generator<String> {

    private val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9') + (' '..' ')

    override fun generate() = (0 until nextInt(minLength, maxLength))
        .asSequence()
        .map { charPool.random() }
        .joinToString(separator = "")

}