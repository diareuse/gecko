package gecko.android.model

import android.os.Parcelable
import gecko.android.GeckoSource
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Simplified representation of [GeckoData]. It can be used in lists and loaded in large quantities.
 * There's not a large amount of data contained in this object, to load more data use [GeckoSource]
 * */
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
