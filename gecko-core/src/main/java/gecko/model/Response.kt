package gecko.model

import com.google.auto.value.AutoValue
import org.jetbrains.annotations.TestOnly

/**
 * Common representation for a server Response.
 *
 * ### Warning!
 *
 * This object may be quite large, consider truncating [body] where possible.
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

    }

}