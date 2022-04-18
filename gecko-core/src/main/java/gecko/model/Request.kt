package gecko.model

/**
 * Common representation for a server Request.
 *
 * ### Warning!
 *
 * This object may be quite large, consider truncating [body] where possible.
 * */
data class Request(
    val method: String,
    val url: String,
    val headers: Headers,
    val length: Long,
    val contentType: String,
    val body: ByteArray
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Request

        if (method != other.method) return false
        if (url != other.url) return false
        if (headers != other.headers) return false
        if (length != other.length) return false
        if (contentType != other.contentType) return false
        if (!body.contentEquals(other.body)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = method.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + headers.hashCode()
        result = 31 * result + length.hashCode()
        result = 31 * result + contentType.hashCode()
        result = 31 * result + body.contentHashCode()
        return result
    }

}