package gecko

import gecko.model.NetworkMetadata

class GeckoBase64Wrapper(
    private val source: Gecko,
    private val encoder: Base64Encoder
) : Gecko {

    override fun process(metadata: NetworkMetadata): ByteArray = source.process(metadata)
        .let(encoder::encode)

}