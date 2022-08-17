package gecko.model

/**
 * Uses provided [origin] to return its values as implementation of this class. Is effectively a delegate to [origin].
 * */
abstract class RequestDecorated(
    private val origin: Request
) : Request() {

    override val method: String
        get() = origin.method
    override val url: String
        get() = origin.url
    override val headers: Headers
        get() = origin.headers
    override val length: Long
        get() = origin.length
    override val contentType: String
        get() = origin.contentType
    override val body: ByteArray
        get() = origin.body

}