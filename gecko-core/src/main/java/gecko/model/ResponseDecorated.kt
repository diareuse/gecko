package gecko.model

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