package gecko.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class GeckoMetadata(
    val id: Long,
    val createdAt: Date,
    val requestMethod: String,
    val requestUrl: String,
    val requestLength: Long,
    val responseCode: Int,
    val responseLength: Long
) : Parcelable
