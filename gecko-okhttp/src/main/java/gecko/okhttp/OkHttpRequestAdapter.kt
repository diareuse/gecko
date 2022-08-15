package gecko.okhttp

import gecko.model.Headers
import gecko.model.Request
import gecko.okhttp.GeckoInterceptor.Companion.emptyByteArray
import okhttp3.RequestBody
import okio.Buffer
import okhttp3.Request as OkHttpRequest

private class OkHttpRequestAdapter(
    private val request: OkHttpRequest
) : Request() {

    override val method: String
        get() = request.method
    override val url: String
        get() = request.url.toString()
    override val headers: Headers
        get() = request.headers
    override val length: Long
        get() = request.body?.contentLength() ?: 0
    override val contentType: String
        get() = request.body?.contentType().toString()
    override val body: ByteArray
        get() = request.body?.cloneBytes() ?: emptyByteArray

    // ---

    private fun RequestBody.cloneBytes(): ByteArray {
        val source = Buffer().apply { writeTo(this) }
        source.request(Long.MAX_VALUE)
        return source.buffer.readByteArray()
    }

}

internal fun OkHttpRequest.asRequest(): Request {
    return OkHttpRequestAdapter(this)
}