package gecko.okhttp.model

import gecko.model.Response
import okhttp3.Headers.Companion.headersOf
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody

internal fun response(
    code: Int = 200,
    message: String = "",
    protocol: String = "HTTP_2",
    headers: Iterable<Pair<String, String>> = listOf(),
    length: Long = 0,
    contentType: String = "null",
    body: ByteArray = ByteArray(0)
): Response = object : Response() {
    override val code: Int
        get() = code
    override val message: String
        get() = message
    override val protocol: String
        get() = protocol
    override val headers: gecko.model.Headers
        get() = headers
    override val length: Long
        get() = length
    override val contentType: String
        get() = contentType
    override val body: ByteArray
        get() = body

}

fun Response.copy(
    code: Int = this.code,
    message: String = this.message,
    protocol: String = this.protocol,
    headers: Iterable<Pair<String, String>> = this.headers,
    length: Long = this.length,
    contentType: String = this.contentType,
    body: ByteArray = this.body
) = response(
    code = code,
    message = message,
    protocol = protocol,
    headers = headers,
    length = length,
    contentType = contentType,
    body = body
)

internal fun Response.toResponse(request: Request) = okhttp3.Response.Builder()
    .code(code)
    .message(message)
    .protocol(Protocol.valueOf(protocol))
    .headers(
        headersOf(*headers
            .flatMap { listOf(it.first, it.second) }
            .toTypedArray()))
    .body(body.toResponseBody())
    .request(request)
    .build()
