package gecko.okhttp.model

import gecko.model.Request
import okhttp3.Headers
import okhttp3.RequestBody.Companion.toRequestBody

fun request(
    method: String = "get",
    url: String = "https://example.org/",
    headers: List<Pair<String, String>> = listOf(),
    length: Long = 0,
    contentType: String = "null",
    body: ByteArray = ByteArray(0)
) = Request(
    method = method,
    url = url,
    headers = headers,
    length = length,
    contentType = contentType,
    body = body
)

fun Request.toRequest() = okhttp3.Request.Builder()
    .method(method, body.toRequestBody())
    .url(url)
    .headers(
        Headers.headersOf(*headers
            .flatMap { listOf(it.first, it.second) }
            .toTypedArray()))
    .build()