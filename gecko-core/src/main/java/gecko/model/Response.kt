package gecko.model

data class Response(
    val code: Int,
    val message: String,
    val protocol: String,
    val headers: Headers,
    val length: Long,
    val contentType: String,
    val body: ByteArray
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Response

        if (code != other.code) return false
        if (message != other.message) return false
        if (protocol != other.protocol) return false
        if (headers != other.headers) return false
        if (length != other.length) return false
        if (contentType != other.contentType) return false
        if (!body.contentEquals(other.body)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code
        result = 31 * result + message.hashCode()
        result = 31 * result + protocol.hashCode()
        result = 31 * result + headers.hashCode()
        result = 31 * result + length.hashCode()
        result = 31 * result + contentType.hashCode()
        result = 31 * result + body.contentHashCode()
        return result
    }

}