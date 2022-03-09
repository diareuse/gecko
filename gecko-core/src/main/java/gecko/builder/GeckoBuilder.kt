package gecko.builder

import gecko.*

@PublishedApi
internal class GeckoBuilder : GeckoConfiguration {

    override lateinit var domain: String
    override lateinit var encoder: Base64Encoder
    override lateinit var adapter: MetadataAdapter
    override lateinit var logger: Logger
    private val wrappers = mutableListOf<GeckoWrapper>()

    override fun addStep(wrapper: GeckoWrapper) {
        wrappers += wrapper
    }

    fun build(): Gecko {
        var gecko: Gecko
        gecko = GeckoFormatter(adapter)
        gecko = GeckoCompressor(gecko)
        gecko = GeckoBase64Wrapper(gecko, encoder)
        gecko = GeckoUrlAssembly(gecko, domain)
        gecko = GeckoLogging(gecko, logger)
        for (wrapper in wrappers)
            gecko = wrapper.wrap(gecko)
        return gecko
    }

}