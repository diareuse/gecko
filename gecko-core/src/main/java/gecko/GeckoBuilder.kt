package gecko

import java.util.*

class GeckoBuilder private constructor(
    @PublishedApi internal var gecko: Gecko
) {

    inline fun step(body: (Gecko) -> Gecko) = apply { gecko = body(gecko) }

    fun build(): Gecko = gecko

    companion object {

        fun Gecko.newBuilder() = GeckoBuilder(this)

        fun getDefault() = GeckoFormatter(MetadataAdapterAscii()).newBuilder()
            .step { GeckoCompressor(it) }
            .step { GeckoBase64Wrapper(it, Base64.getEncoder()) }
            .step { GeckoUrlAssembly(it, "diareuse.github.io/gecko") }

    }

}