package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail
import gecko.model.asString
import gecko.model.asTail

class GeckoUrlAssembly(
    private val source: Gecko,
    private val domain: String
) : Gecko {

    override fun process(metadata: NetworkMetadata): Tail = "https://$domain/?q=%s"
        .format(source.process(metadata).asString())
        .asTail()

}