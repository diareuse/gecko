package gecko.model

import com.google.auto.value.AutoValue
import gecko.interceptor.ResponseInterceptor
import gecko.util.loadServices
import org.jetbrains.annotations.TestOnly

/**
 * Common representation for a server Response.
 * */
@AutoValue
abstract class Response {

    abstract val code: Int
    abstract val message: String
    abstract val protocol: String
    abstract val headers: Headers
    abstract val length: Long
    abstract val contentType: String
    abstract val body: ByteArray

    fun snapshot(
        code: Int = this.code,
        message: String = this.message,
        protocol: String = this.protocol,
        headers: Headers = this.headers,
        length: Long = this.length,
        contentType: String = this.contentType,
        body: ByteArray = this.body
    ): Response = AutoValue_Response(
        code, message, protocol, headers, length, contentType, body
    )

    companion object {

        @TestOnly
        operator fun invoke(
            code: Int,
            message: String,
            protocol: String,
            headers: Headers,
            length: Long,
            contentType: String,
            body: ByteArray
        ): Response = AutoValue_Response(
            code, message, protocol, headers, length, contentType, body
        )

        fun wrap(response: Response): Response {
            var out = response
            for (interceptor in loadServices<ResponseInterceptor>()) {
                out = interceptor.intercept(out)
            }
            return out
        }

    }

}