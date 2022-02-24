package gecko

import gecko.model.NetworkMetadata
import java.util.*

class GeckoBase64Wrapper(
    private val source: Gecko,
    private val encoder: Base64.Encoder
) : Gecko {

    override fun process(metadata: NetworkMetadata): ByteArray = source.process(metadata)
        .let(encoder::encode)

}