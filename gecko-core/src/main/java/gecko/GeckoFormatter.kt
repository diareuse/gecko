package gecko

import gecko.model.NetworkMetadata

class GeckoFormatter(
    private val adapter: MetadataAdapter
) : Gecko {

    override fun process(metadata: NetworkMetadata): ByteArray {
        return adapter.adapt(metadata).encodeToByteArray()
    }

}