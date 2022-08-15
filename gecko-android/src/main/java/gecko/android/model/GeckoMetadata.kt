package gecko.android.model

import gecko.android.GeckoSource
import java.util.*

/**
 * Simplified representation of [GeckoData]. It can be used in lists and loaded in large quantities.
 * There's not a large amount of data contained in this object, to load more data use [GeckoSource]
 * */
abstract class GeckoMetadata {

    abstract val id: Long
    abstract val createdAt: Date
    abstract val requestMethod: String
    abstract val requestUrl: String
    abstract val requestLength: Long
    abstract val responseCode: Int
    abstract val responseLength: Long

}
