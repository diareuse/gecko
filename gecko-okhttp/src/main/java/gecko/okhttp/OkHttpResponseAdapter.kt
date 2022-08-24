package gecko.okhttp

import gecko.model.Headers
import gecko.model.Response
import gecko.okhttp.GeckoInterceptor.Companion.emptyByteArray
import okhttp3.ResponseBody
import okio.Buffer
import okio.GzipSource
import okhttp3.Response as OkHttpResponse

private class OkHttpResponseAdapter(
    private val response: OkHttpResponse
) : Response() {

    override val code: Int
        get() = response.code
    override val message: String
        get() = response.message
    override val protocol: String
        get() = response.protocol.name
    override val headers: Headers
        get() = response.headers
    override val length: Long
        get() = response.body?.contentLength() ?: 0
    override val contentType: String
        get() = response.body?.contentType().toString()
    override val body: ByteArray = response.body?.cloneBytes(response.isGzipped) ?: emptyByteArray

    // ---

    private fun ResponseBody.cloneBytes(isGzipped: Boolean): ByteArray {
        val source = source()
        source.request(Long.MAX_VALUE)
        var buffer = source.buffer
        if (isGzipped) {
            GzipSource(buffer.clone()).use { gzippedResponseBody ->
                buffer = Buffer()
                buffer.writeAll(gzippedResponseBody)
            }
        }
        return buffer.clone().readByteArray()
    }

    private val okhttp3.Response.isGzipped
        get() = "gzip".equals(headers["Content-Encoding"], ignoreCase = true)

}

internal fun OkHttpResponse.asResponse(): Response {
    return OkHttpResponseAdapter(this)
}