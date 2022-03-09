package gecko.okhttp

import gecko.Gecko
import gecko.model.NetworkMetadata
import gecko.model.Request
import gecko.model.Response
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import okio.GzipSource

class GeckoInterceptor(
    private val gecko: Gecko
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val metadata = NetworkMetadata(
            request.asGecko(),
            response.asGecko()
        )
        gecko.process(metadata)

        return response
    }

    private fun okhttp3.Request.asGecko(): Request = Request(
        method = method,
        url = url.toString(),
        headers = headers.toList(),
        length = body?.contentLength() ?: 0,
        contentType = body?.contentType().toString(),
        body = body?.cloneBytes() ?: emptyByteArray
    )

    private fun okhttp3.Response.asGecko(): Response = Response(
        code = code,
        message = message,
        protocol = protocol.name,
        headers = headers.toList(),
        length = body?.contentLength() ?: 0,
        contentType = body?.contentType().toString(),
        body = body?.cloneBytes(isGzipped) ?: emptyByteArray
    )

    private fun RequestBody.cloneBytes(): ByteArray {
        val source = Buffer().apply { writeTo(this) }
        source.request(Long.MAX_VALUE)
        return source.buffer.readByteArray()
    }

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

    companion object {

        private val emptyByteArray = ByteArray(0)

    }

}
