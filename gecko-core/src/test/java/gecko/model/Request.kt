package gecko.model

internal val httpMethods =
    listOf("get", "head", "post", "put", "delete", "connect", "options", "trace", "patch")

internal fun request(
    method: String = httpMethods.random(),
    url: String = "https://example.org/api/foo",
    headers: Headers = emptyList(),
    length: Long = 0,
    contentType: String = "",
    body: ByteArray = ByteArray(0)
) = Request(
    method = method,
    url = url,
    headers = headers,
    length = length,
    contentType = contentType,
    body = body
)