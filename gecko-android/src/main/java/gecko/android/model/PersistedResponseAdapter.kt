package gecko.android.model

import gecko.model.Headers
import gecko.model.Response

private class PersistedResponseAdapter(
    private val response: Response,
    private val id: Long
) : PersistedResponse() {

    override val idCall: Long
        get() = id
    override val code: Int
        get() = response.code
    override val message: String
        get() = response.message
    override val protocol: String
        get() = response.protocol
    override val headers: Headers
        get() = response.headers
    override val length: Long
        get() = response.length
    override val contentType: String
        get() = response.contentType
    override val body: String
        get() = response.body.decodeToString()

}

internal fun Response.asPersistedResponse(id: Long): PersistedResponse {
    return PersistedResponseAdapter(this, id)
}