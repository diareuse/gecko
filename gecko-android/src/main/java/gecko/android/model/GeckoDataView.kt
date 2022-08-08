package gecko.android.model

import gecko.model.Headers
import java.util.*

private class GeckoDataView(
    private val view: CallView
) : GeckoData() {

    override val id: Long
        get() = view.id
    override val link: String
        get() = view.link
    override val createdAt: Date
        get() = view.createdAt
    override val requestMethod: String
        get() = view.requestMethod
    override val requestUrl: String
        get() = view.requestUrl
    override val requestHeaders: Set<String>
        get() = view.requestHeaders.asSet()
    override val requestLength: Long
        get() = view.requestLength
    override val requestContentType: String
        get() = view.requestContentType
    override val requestBody: String
        get() = view.requestBody
    override val responseCode: Int
        get() = view.responseCode
    override val responseMessage: String
        get() = view.responseMessage
    override val responseProtocol: String
        get() = view.responseProtocol
    override val responseHeaders: Set<String>
        get() = view.responseHeaders.asSet()
    override val responseLength: Long
        get() = view.responseLength
    override val responseContentType: String
        get() = view.responseContentType
    override val responseBody: String
        get() = view.responseBody

    private fun Headers.asSet() = asSequence()
        .map { (key, value) -> "$key: $value" }
        .toSet()

}

internal fun CallView.asGeckoData(): GeckoData {
    return GeckoDataView(this)
}
