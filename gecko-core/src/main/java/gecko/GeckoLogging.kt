package gecko

import gecko.model.NetworkMetadata

class GeckoLogging(
    private val source: Gecko,
    private val logger: Logger
) : Gecko {

    override fun process(metadata: NetworkMetadata): ByteArray {
        return source.process(metadata).also {
            logger.log(it.decodeToString())
        }
    }

}