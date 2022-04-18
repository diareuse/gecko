package gecko.android.example.generator

internal class GeneratorCode : Generator<Int> {

    private val codes = 200 until 600

    override fun generate(): Int {
        return codes.random()
    }

}