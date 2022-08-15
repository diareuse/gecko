package gecko.android.model

import kotlinx.parcelize.RawValue
import java.util.*

abstract class GeckoData {

    abstract val id: Long
    abstract val link: String
    abstract val createdAt: Date
    abstract val requestMethod: String
    abstract val requestUrl: String
    abstract val requestHeaders: @RawValue Set<String>
    abstract val requestLength: Long
    abstract val requestContentType: String
    abstract val requestBody: String
    abstract val responseCode: Int
    abstract val responseMessage: String
    abstract val responseProtocol: String
    abstract val responseHeaders: @RawValue Set<String>
    abstract val responseLength: Long
    abstract val responseContentType: String
    abstract val responseBody: String

}
