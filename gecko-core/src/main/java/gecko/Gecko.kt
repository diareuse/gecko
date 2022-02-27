package gecko

import gecko.model.NetworkMetadata
import gecko.model.Tail

interface Gecko {

    fun process(metadata: NetworkMetadata): Tail

}