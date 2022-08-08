package gecko.android.model

import java.util.*

private class GeckoMetadataView(
    private val view: CallMetadataView
) : GeckoMetadata() {

    override val id: Long
        get() = view.id
    override val createdAt: Date
        get() = view.createdAt
    override val requestMethod: String
        get() = view.requestMethod
    override val requestUrl: String
        get() = view.requestUrl
    override val requestLength: Long
        get() = view.requestLength
    override val responseCode: Int
        get() = view.responseCode
    override val responseLength: Long
        get() = view.responseLength

}

internal fun CallMetadataView.asGeckoMetadata(): GeckoMetadata {
    return GeckoMetadataView(this)
}