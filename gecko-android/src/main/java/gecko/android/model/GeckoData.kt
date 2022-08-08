package gecko.android.model

import gecko.model.AbstractGeckoModel
import kotlinx.parcelize.RawValue
import java.util.*

open class GeckoData(
    open val id: Long = 0,
    open val link: String = "",
    open val createdAt: Date = Date(),
    open val requestMethod: String = "",
    open val requestUrl: String = "",
    open val requestHeaders: @RawValue Set<String> = emptySet(),
    open val requestLength: Long = 0,
    open val requestContentType: String = "",
    open val requestBody: String = "",
    open val responseCode: Int = 0,
    open val responseMessage: String = "",
    open val responseProtocol: String = "",
    open val responseHeaders: @RawValue Set<String> = emptySet(),
    open val responseLength: Long = 0,
    open val responseContentType: String = "",
    open val responseBody: String = ""
) : AbstractGeckoModel() {

    override val properties
        get() = sequenceOf(
            ::id,
            ::link,
            ::createdAt,
            ::requestMethod,
            ::requestUrl,
            ::requestHeaders,
            ::requestLength,
            ::requestContentType,
            ::requestBody,
            ::responseCode,
            ::responseMessage,
            ::responseProtocol,
            ::responseHeaders,
            ::responseLength,
            ::responseContentType,
            ::responseBody
        )

}
