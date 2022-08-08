package gecko.model

import kotlin.reflect.KProperty0

/**
 * Common representation for a server Response.
 *
 * ### Warning!
 *
 * This object may be quite large, consider truncating [body] where possible.
 * */
open class Response(
    open val code: Int,
    open val message: String,
    open val protocol: String,
    open val headers: Headers,
    open val length: Long,
    open val contentType: String,
    @Suppress("ArrayInDataClass")
    open val body: ByteArray
) : AbstractGeckoModel() {

    override val properties: Sequence<KProperty0<Any?>>
        get() = sequenceOf(
            ::code,
            ::message,
            ::protocol,
            ::headers,
            ::length,
            ::contentType,
            ::body
        )

    fun copy(
        code: Int = this.code,
        message: String = this.message,
        protocol: String = this.protocol,
        headers: Headers = this.headers,
        length: Long = this.length,
        contentType: String = this.contentType,
        body: ByteArray = this.body
    ) = Response(
        code = code,
        message = message,
        protocol = protocol,
        headers = headers,
        length = length,
        contentType = contentType,
        body = body
    )

}