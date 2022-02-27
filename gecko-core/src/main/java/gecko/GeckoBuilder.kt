package gecko

import java.util.*

class GeckoBuilder private constructor(
    @PublishedApi internal var gecko: Gecko
) {

    inline fun step(body: (Gecko) -> Gecko) = apply { gecko = body(gecko) }

    fun build(): Gecko = gecko

    companion object {

        fun Gecko.buildUpon() = GeckoBuilder(this)

        fun getDefault() = GeckoFormatter(MetadataAdapterAscii()).buildUpon()
            .step { GeckoCompressor(it) }
            .step { GeckoBase64Wrapper(it) { b -> Base64.getUrlEncoder().encode(b) } }
            .step { GeckoUrlAssembly(it, "diareuse.github.io/gecko") }

    }

}