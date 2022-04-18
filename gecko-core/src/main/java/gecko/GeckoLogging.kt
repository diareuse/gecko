package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.asString

/**
 * Uses [Logger] to send information about the request made with all the necessary information.
 * Can be injected to the flow at any time, that way it's not deterministic what the actually
 * logged information might be.
 * */
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