package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.mapBytes

class GeckoBase64Wrapper(
    private val source: Gecko,
    private val encoder: Base64Encoder
) : Gecko {

    override fun process(metadata: NetworkMetadata): Tail = source.process(metadata)
        .mapBytes(encoder::encode)

}