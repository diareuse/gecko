package gecko.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
data class GeckoData(
    val id: Long,
    val link: String,
    val createdAt: Date,
    val requestMethod: String,
    val requestUrl: String,
    val requestHeaders: @RawValue Set<String>,
    val requestLength: Long,
    val requestContentType: String,
    val requestBody: String,
    val responseCode: Int,
    val responseMessage: String,
    val responseProtocol: String,
    val responseHeaders: @RawValue Set<String>,
    val responseLength: Long,
    val responseContentType: String,
    val responseBody: String
) : Parcelable
