package gecko.model

internal val httpCodes = 200 until 600

internal fun response(
    code: Int = httpCodes.random(),
    message: String = "",
    protocol: String = "HTTP/2",
    headers: Headers = emptyList(),
    length: Long = 0,
    contentType: String = "",
    body: ByteArray = ByteArray(0)
) = Response(
    code = code,
    message = message,
    protocol = protocol,
    headers = headers,
    length = length,
    contentType = contentType,
    body = body
)