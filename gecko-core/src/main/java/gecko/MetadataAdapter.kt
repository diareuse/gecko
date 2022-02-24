package gecko

import gecko.model.NetworkMetadata

interface MetadataAdapter {

    fun adapt(metadata: NetworkMetadata): String

}