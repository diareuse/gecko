package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.mapBytes

/**
 * Uses previous step's result to calculate Base64 representation of a given result.
 * */
class GeckoBase64Wrapper(
    private val source: Gecko,
    private val encoder: Base64Encoder
) : Gecko {

    override fun process(metadata: NetworkMetadata): Tail = source.process(metadata)
        .mapBytes(encoder::encode)

}