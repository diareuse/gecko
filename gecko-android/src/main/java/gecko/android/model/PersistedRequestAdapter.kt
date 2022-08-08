package gecko.android.model

import gecko.model.Headers
import gecko.model.Request

private class PersistedRequestAdapter(
    private val request: Request,
    private val id: Long
) : PersistedRequest() {

    override val idCall: Long
        get() = id
    override val method: String
        get() = request.method
    override val url: String
        get() = request.url
    override val headers: Headers
        get() = request.headers
    override val length: Long
        get() = request.length
    override val contentType: String
        get() = request.contentType
    override val body: String
        get() = request.body.decodeToString()

}

internal fun Request.asPersistedRequest(id: Long): PersistedRequest {
    return PersistedRequestAdapter(this, id)
}