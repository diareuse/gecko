package gecko.model

/**
 * Common representation for a server Request.
 *
 * ### Warning!
 *
 * This object may be quite large, consider truncating [body] where possible.
 * */
open class Request(
    open val method: String,
    open val url: String,
    open val headers: Headers,
    open val length: Long,
    open val contentType: String,
    open val body: ByteArray
) : AbstractGeckoModel() {

    override val properties
        get() = sequenceOf(
            ::method,
            ::url,
            ::headers,
            ::length,
            ::contentType,
            ::body,
        )

}