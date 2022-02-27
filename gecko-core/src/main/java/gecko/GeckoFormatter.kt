package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.asTail

class GeckoFormatter(
    private val adapter: MetadataAdapter
) : Gecko {

    override fun process(metadata: NetworkMetadata): Tail {
        return adapter.adapt(metadata).asTail()
    }

}