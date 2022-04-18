package gecko.okhttp.model

import gecko.model.Response
import okhttp3.Headers
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody

internal fun response(
    code: Int = 200,
    message: String = "",
    protocol: String = "HTTP_2",
    headers: List<Pair<String, String>> = listOf(),
    length: Long = 0,
    contentType: String = "null",
    body: ByteArray = ByteArray(0)
) = Response(
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
        Headers.headersOf(*headers
            .flatMap { listOf(it.first, it.second) }
            .toTypedArray()))
    .body(body.toResponseBody())
    .request(request)
    .build()
