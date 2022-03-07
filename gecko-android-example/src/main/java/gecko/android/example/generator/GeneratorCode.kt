package gecko.android.example.generator

class GeneratorCode : Generator<Int> {

    private val codes = 200 until 600

    override fun generate(): Int {
        return codes.random()
    }

}