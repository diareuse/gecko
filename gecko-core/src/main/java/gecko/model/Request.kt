package gecko.model

import com.google.auto.value.AutoValue
import org.jetbrains.annotations.TestOnly

/**
 * Common representation for a server Request.
 * */
@AutoValue
abstract class Request {

    abstract val method: String
    abstract val url: String
    abstract val headers: Headers
    abstract val length: Long
    abstract val contentType: String
    abstract val body: ByteArray

    fun snapshot(
        method: String = this.method,
        url: String = this.url,
        headers: Headers = this.headers,
        length: Long = this.length,
        contentType: String = this.contentType,
        body: ByteArray = this.body
    ): Request = AutoValue_Request(
        method, url, headers, length, contentType, body
    )

    companion object {

        @TestOnly
        operator fun invoke(
            method: String,
            url: String,
            headers: Headers,
            length: Long,
            contentType: String,
            body: ByteArray
        ): Request = AutoValue_Request(
            method, url, headers, length, contentType, body
        )

    }

}