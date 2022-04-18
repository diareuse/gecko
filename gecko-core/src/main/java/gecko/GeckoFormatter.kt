package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.asTail

/**
 * Transforms given metadata into a proprietary format
 * */
class GeckoFormatter(
    private val adapter: MetadataAdapter
) : Gecko {

    override fun process(metadata: NetworkMetadata): Tail {
        return adapter.adapt(metadata).asTail()
    }

}