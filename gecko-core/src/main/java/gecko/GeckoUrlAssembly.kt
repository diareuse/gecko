package gecko

import gecko.model.NetworkMetadata

class GeckoUrlAssembly(
    private val source: Gecko,
    private val domain: String
) : Gecko {

    override fun process(metadata: NetworkMetadata) = "https://$domain/%s"
        .format(source.process(metadata).decodeToString())
        .encodeToByteArray()

}