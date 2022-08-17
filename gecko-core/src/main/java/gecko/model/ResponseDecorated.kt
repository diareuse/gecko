package gecko.model

/**
 * Uses provided [origin] to return its values as implementation of this class. Is effectively a delegate to [origin].
 * */
abstract class ResponseDecorated(
    private val origin: Response
) : Response() {

    override val code: Int
        get() = origin.code
    override val message: String
        get() = origin.message
    override val protocol: String
        get() = origin.protocol
    override val headers: Headers
        get() = origin.headers
    override val length: Long
        get() = origin.length
    override val contentType: String
        get() = origin.contentType
    override val body: ByteArray
        get() = origin.body

}