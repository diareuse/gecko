package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.asString
import gecko.model.asTail

/**
 * Uses previous step's result in a URL template. It takes an assumption that the previous result
 * is URL safe.
 * */
class GeckoUrlAssembly(
    private val source: Gecko,
    private val domain: String
) : Gecko {

    override fun process(metadata: NetworkMetadata): Tail = "https://$domain/?q=%s"
        .format(source.process(metadata).asString())
        .asTail()

}