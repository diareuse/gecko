package gecko

import gecko.model.NetworkMetadata

interface Gecko {

    fun process(metadata: NetworkMetadata): ByteArray

}