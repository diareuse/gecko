package gecko.android.example.generator

class GeneratorMethod : Generator<String> {

    private val httpMethods = listOf("GET", "POST", "HEAD", "DELETE", "PUT", "PATCH")

    override fun generate(): String {
        return httpMethods.random()
    }

}