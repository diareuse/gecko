package gecko.android.example.generator

import gecko.model.Request

internal class GeneratorRequest(
    private val method: Generator<String>,
    private val body: Generator<String>
) : Generator<Request> {

    override fun generate(): Request {
        val body = body.generate()
        return Request(
            method = method.generate(),
            url = "https://api.github.com/v3/foo/bar/",
            headers = listOf("Authorization" to "Bearer", "User-Agent" to "Gecko"),
            length = body.length.toLong(),
            contentType = "application/json",
            body = body.encodeToByteArray()
        )
    }

}