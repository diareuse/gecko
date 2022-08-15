package gecko.okhttp.model

import gecko.model.Request
import okhttp3.Headers.Companion.headersOf
import okhttp3.RequestBody.Companion.toRequestBody

internal fun request(
    method: String = "get",
    url: String = "https://example.org/",
    headers: List<Pair<String, String>> = listOf(),
    length: Long = 0,
    contentType: String = "null",
    body: ByteArray = ByteArray(0)
) = object : Request() {
    override val method: String
        get() = method
    override val url: String
        get() = url
    override val headers: gecko.model.Headers
        get() = headers
    override val length: Long
        get() = length
    override val contentType: String
        get() = contentType
    override val body: ByteArray
        get() = body

}

internal fun Request.toRequest() = okhttp3.Request.Builder()
    .method(method, body.toRequestBody())
    .url(url)
    .headers(
        headersOf(*headers
            .flatMap { listOf(it.first, it.second) }
            .toTypedArray()))
    .build()