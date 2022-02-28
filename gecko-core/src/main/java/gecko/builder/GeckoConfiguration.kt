package gecko.builder

import gecko.Base64Encoder
import gecko.Logger
import gecko.MetadataAdapter

@GeckoDSL
interface GeckoConfiguration {

    var domain: String
    var encoder: Base64Encoder
    var adapter: MetadataAdapter
    var logger: Logger

    fun addStep(wrapper: GeckoWrapper)

}