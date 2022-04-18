package gecko.android.example.generator

import gecko.model.Response

internal class GeneratorResponse(
    private val code: Generator<Int>,
    private val body: Generator<String>
) : Generator<Response> {

    override fun generate(): Response {
        val body = body.generate()
        return Response(
            code = code.generate(),
            message = "OK",
            protocol = "HTTP/2",
            headers = listOf("X-Hello" to "*waves*"),
            length = body.length.toLong(),
            contentType = "application/json",
            body = body.encodeToByteArray()
        )
    }

}