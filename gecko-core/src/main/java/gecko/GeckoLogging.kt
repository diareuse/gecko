package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.asString

class GeckoLogging(
    private val source: Gecko,
    private val logger: Logger
) : Gecko {

    override fun process(metadata: NetworkMetadata): Tail {
        return source.process(metadata).also {
            val method = metadata.request.method
            val url = metadata.request.url
            val code = metadata.response.code
            logger.log("-> $method | $code | $url")
            logger.log(it.asString())
        }
    }

}