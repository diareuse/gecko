package gecko.android.model

import gecko.android.GeckoSource
import gecko.model.AbstractGeckoModel
import java.util.*

/**
 * Simplified representation of [GeckoData]. It can be used in lists and loaded in large quantities.
 * There's not a large amount of data contained in this object, to load more data use [GeckoSource]
 * */
open class GeckoMetadata(
    open val id: Long = 0,
    open val createdAt: Date = Date(),
    open val requestMethod: String = "",
    open val requestUrl: String = "",
    open val requestLength: Long = 0,
    open val responseCode: Int = 0,
    open val responseLength: Long = 0
) : AbstractGeckoModel() {

    override val properties
        get() = sequenceOf(
            ::id,
            ::createdAt,
            ::requestMethod,
            ::requestUrl,
            ::requestLength,
            ::responseCode,
            ::responseLength,
        )
}
