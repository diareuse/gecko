package gecko.android.example.generator

import gecko.model.NetworkMetadata
import gecko.model.Request
import gecko.model.Response

internal class GeneratorNetworkMetadata(
    private val request: Generator<Request>,
    private val response: Generator<Response>
) : Generator<NetworkMetadata> {

    override fun generate() = NetworkMetadata(
        request = request.generate(),
        response = response.generate()
    )

}

internal fun networkMetadataGenerator(): Generator<NetworkMetadata> {
    val string = GeneratorString()
    val json = GeneratorJsonObject(string)
    val array = GeneratorJsonArray(json)
    val body = GeneratorBody(array, json)
    val method = GeneratorMethod()
    val request = GeneratorRequest(method, body)
    val code = GeneratorCode()
    val response = GeneratorResponse(code, body)
    return GeneratorNetworkMetadata(request, response)
}
